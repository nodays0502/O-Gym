package com.B305.ogym.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import com.B305.ogym.common.config.SecurityConfig;
import com.B305.ogym.controller.dto.PTDto.SaveReservationRequest;
import com.B305.ogym.service.PTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


@ExtendWith(RestDocumentationExtension.class) // JUnit 5 사용시 문서 스니펫 생성용
@WebMvcTest(controllers = PTApiController.class, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
//@ActiveProfiles("test") // 테스트에서 사용할 profile
@MockBean(JpaMetamodelMappingContext.class) // @EnableJPaAuditing 사용시 추가해야하는 어노테이션
//@AutoConfigureMockMvc(addFilters = false)
class PTApiControllerTest {

    @MockBean
    private PTService ptService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .apply(sharedHttpSession())
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }

    private SaveReservationRequest createReservation() {
        return SaveReservationRequest.builder()
            .ptTeacherEmail("teacher@naver.com")
            .build();
    }

//    @WithUserDetails("testUser")
    @DisplayName("PT 예약 성공")
    @Test
    public void PT_Reservation_Success() throws Exception {
        //given
        var request = createReservation();

        //when
        doNothing().when(ptService).makeReservation(any(), request);

        //then
//        mockMvc.perform(post("/api/pt/reservation")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(request)))
//            .andDo(print())
//            .andExpect(status().isOk()) // 201 isCreated()
//            .andDo(
//                document(
//                    "ptApi/reservation/successful",
//                    requestFields(
//                        fieldWithPath("email").type(JsonFieldType.STRING)
//                            .description("The user's email address"),
//                        fieldWithPath("password").type(JsonFieldType.STRING)
//                            .description("The user's password"),
//                        fieldWithPath("username").type(JsonFieldType.STRING)
//                            .description("The user's username"),
//                        fieldWithPath("nickname").type(JsonFieldType.STRING)
//                            .description("The user's nickname"),
//                        fieldWithPath("gender").type(JsonFieldType.NUMBER)
//                            .description("The user's gender"),
//                        fieldWithPath("tel").type(JsonFieldType.STRING)
//                            .description("The user's tel"),
//                        fieldWithPath("zipCode").type(JsonFieldType.STRING)
//                            .description("The user's zipCode"),
//                        fieldWithPath("street").type(JsonFieldType.STRING)
//                            .description("The user's street"),
//                        fieldWithPath("detailedAddress").type(JsonFieldType.STRING)
//                            .description("The user's detailedAddress"),
//                        fieldWithPath("role").type(JsonFieldType.STRING)
//                            .description("The user's role"),
//                        fieldWithPath("major").type(JsonFieldType.STRING)
//                            .description("The user's major"),
//                        fieldWithPath("certificates").type(JsonFieldType.ARRAY)
//                            .description("The user's certificates"),
//                        fieldWithPath("careers").type(JsonFieldType.ARRAY)
//                            .description("The user's careers"),
//                        fieldWithPath("price").type(JsonFieldType.NUMBER)
//                            .description("The user's price"),
//                        fieldWithPath("description").type(JsonFieldType.STRING)
//                            .description("The user's description"),
//                        fieldWithPath("snsAddrs").type(JsonFieldType.NULL)
//                            .description("The user's snsAddrs")
//                    ).and(
//                        fieldWithPath("monthlyHeights").type(JsonFieldType.NULL)
//                            .description("트레이너의 경우 입력받지 않습니다."),
//                        fieldWithPath("monthlyWeights").type(JsonFieldType.NULL)
//                            .description("트레이너의 경우 입력받지 않습니다.")
//                    )));
    }

}