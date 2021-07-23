package com.B305.ogym.controller;

import com.B305.ogym.common.config.SecurityConfig;
import com.B305.ogym.controller.dto.UserDto;
import com.B305.ogym.domain.autority.Authority;
import com.B305.ogym.domain.autority.AuthorityRepository;
import com.B305.ogym.domain.users.common.UserBase;
import com.B305.ogym.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(MockitoExtension.class)
class UserApiControllerTest {

    @MockBean
    private UserService userService;


    @Mock
    private AuthorityRepository authorityRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityConfig securityConfig;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;


    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
        authorityRepository.save(Authority.builder().authorityName("ROLE_USER").build());
    }

    @DisplayName("회원가입시 잘못된 입력값 테스트")
    @Test
    public void signupByWrongInputTest() throws Exception{
        //given
        final UserDto userDto = UserDto.builder()
                                    .email("email")
                                    .password("password")
                                    .nickname("nickname")
                                    .build();
        //when
//        String content = objectMapper.writeValueAsString(userDto);
        UserBase signup = userService.signup(userDto);
        Assertions.assertThat(signup.getNickname()).isEqualTo("nickname");
        Assertions.assertThat(signup.getEmail()).isEqualTo("email");
        Assertions.assertThat(signup.getAuthority()).isEqualTo("ROLE_USER");

        //then

    }
}