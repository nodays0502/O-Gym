package com.B305.ogym.common.jwt;

import com.B305.ogym.common.util.RedisUtil;
import com.B305.ogym.controller.dto.AuthDto.TokenDto;
import com.B305.ogym.domain.users.UserRepository;
import com.B305.ogym.domain.users.common.UserBase;
import com.B305.ogym.exception.user.UnauthorizedException;
import com.B305.ogym.exception.user.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "role";

    private final String secret;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final RedisUtil redisUtil;

    private Key key;

    private final UserRepository userRepository;

    public TokenProvider(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
        @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds,
        UserRepository userRepository, RedisUtil redisUtil) {
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000;
        this.userRepository = userRepository;
        this.redisUtil = redisUtil;
    }

    /*
     * ????????? ??? ??????
     */
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /*
     * ????????? ???????????? ?????? ????????? ???????????? ?????????
     */
    public TokenDto createToken(String email,
        String authorities) {

        long now = (new Date()).getTime();

        UserBase user = userRepository.findByEmail(email) // princial.toSTring()
            .orElseThrow(() -> new UserNotFoundException("???????????? ???????????? ???????????? ????????????."));

        String accessToken = Jwts.builder()
            .claim("email", user.getEmail())
            .claim("nickname", user.getNickname())
            .claim(AUTHORITIES_KEY, authorities)
            .setExpiration(new Date(now + accessTokenValidityInMilliseconds))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();

        String refreshToken = Jwts.builder()
            .claim(AUTHORITIES_KEY, authorities)
            .setExpiration(new Date(now + refreshTokenValidityInMilliseconds))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();

        return new TokenDto(accessToken, refreshToken);

    }

    /*
     * ?????? ???????????? ?????????
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.get("email"), null, authorities);
    }

    /*
     * ?????? ????????? ???????????? ?????????
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            logger.info("validate ?????????");
            if (redisUtil.hasKeyBlackList(token)) {
                throw new UnauthorizedException("?????? ????????? ???????????????");
            }
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("????????? JWT ???????????????.");
        } catch (ExpiredJwtException e) {
            logger.info("????????? JWT ???????????????.");
        } catch (UnsupportedJwtException e) {
            logger.info("???????????? ?????? JWT ???????????????.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT ????????? ?????????????????????.");
        } catch (UnauthorizedException e) {
            logger.info("?????? ????????? ???????????????.");
        }
        return false;
    }

    /*
     * ???????????? Claim ???????????? ?????????
     */
    public Claims getClaims(String token) {
        try {
            return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
