package com.B305.ogym.controller;

import static org.mockito.Mockito.doNothing;

import com.B305.ogym.controller.dto.UserDto.SignupRequest;
import com.B305.ogym.domain.autority.Authority;
import com.B305.ogym.domain.autority.AuthorityRepository;
import com.B305.ogym.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(RestDocumentationExtension.class) // JUnit 5 사용시 문서 스니펫 생성용
@WebMvcTest(UserApiController.class) // Controller 관련 빈들만 등록됨
//@ActiveProfiles("test") // 테스트에서 사용할 profile
@MockBean(JpaMetamodelMappingContext.class) // @EnableJPaAuditing 사용시 추가해야하는 어노테이션
@AutoConfigureRestDocs
class UserApiControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("회원가입 - 모든 유효성 검사에 통과했다면, 회원가입 성공")
    @Test
    public void createUser_success() throws Exception {
        //given
        System.out.println(1);
        SignupRequest signupRequest = SignupRequest.builder()
            .email("hello@naver.com")
            .password("1234")
            .username("주현")
            .nickname("펭귄")
            .gender(0)
            .tel("010-0000-0000")
            .zipCode("12345")
            .street("대농로 17")
            .detailedAddress("상세주소상세주소")
            .role("ROLE_PTSTUDENT")
            .build();
        //when
        doNothing().when(userService).signup(signupRequest);

        //then
    }

    @DisplayName("회원가입 - 이메일 중복으로 인한 회원가입 실패")
    @Test
    public void createUser_failure() throws Exception {
        //given

        //when

        //then
    }

}