package com.B305.ogym.service;

import com.B305.ogym.common.exception.DuplicateUserException;
import com.B305.ogym.controller.dto.SignupRequestDto;
import com.B305.ogym.domain.autority.Authority;
import com.B305.ogym.domain.users.UserRepository;
import com.B305.ogym.common.util.SecurityUtil;
import com.B305.ogym.domain.users.common.Address;
import com.B305.ogym.domain.users.common.Gender;
import com.B305.ogym.domain.users.common.UserBase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void signup(SignupRequestDto userDto) {
        if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail())
            != null) {
            throw new DuplicateUserException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
            .authorityName(userDto.getRole())
            .build();

        Gender gender = Gender.MAN;
        if(userDto.getGender() == 1){
            gender = Gender.WOMAN;
        }

        Address address = Address.builder()
            .zipCode(userDto.getZipCode())
            .street(userDto.getStreet())
            .detailedAddress(userDto.getDetailedAddress())
            .build();

        UserBase user = UserBase.builder()
            .email(userDto.getEmail())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .nickname(userDto.getNickname())
            .gender(gender)
            .tel(userDto.getTel())
            .address(address)
            .authority(authority)
            .build();

        userRepository.save(user);
    }

    public UserBase getUserWithAuthorities(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }

    public UserBase getMyUserWithAuthorities() {
        Optional<String> result = SecurityUtil.getCurrentUsername();
        if (result.isEmpty()) {
            return null;
        } else {
            return userRepository.findOneWithAuthoritiesByEmail(result.get());
        }
    }


}
