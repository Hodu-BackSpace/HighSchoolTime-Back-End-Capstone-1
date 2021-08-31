package hodubackspace.highschooltime.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import hodubackspace.highschooltime.api.advice.MemberRestControllerAdvice;
import hodubackspace.highschooltime.api.advice.exception.JoinDuplicateEmailException;
import hodubackspace.highschooltime.api.controller.dto.request.RequestJoinMemberDto;
import hodubackspace.highschooltime.api.service.AuthService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AuthController authController;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(authController)
                .build();
    }

    @Test
    @DisplayName("회원가입 성공")
    public void join_Success() throws Exception {
        //given
        RequestJoinMemberDto requestDto =
                new RequestJoinMemberDto("test@test.com", "abcdefgh", "test",
                        "testNickName", "원광고등학교", 3, 2);

        String requestJson = objectMapper.writeValueAsString(requestDto);

        //when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/auth/join")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson);

        //then
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("중복 이메일 가입시 회원가입에 실패한다.")
    public void join_Failed_When_DuplicateEmail() throws Exception {
        //given
        RequestJoinMemberDto requestDtoA =
                new RequestJoinMemberDto("test@test.com", "abcdefgh", "test",
                        "testNickName", "원광고등학교", 3, 2);

        String requestJsonA = objectMapper.writeValueAsString(requestDtoA);

        MockHttpServletRequestBuilder requestA = MockMvcRequestBuilders
                .post("/api/v1/auth/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonA);

        mockMvc.perform(requestA)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isCreated());

        //when
        RequestJoinMemberDto requestDtoB =
                new RequestJoinMemberDto("test@test.com", "12342155", "test2",
                        "testNickName2", "부송고등학교", 2, 5);

        String requestJsonB = objectMapper.writeValueAsString(requestDtoB);

        MockHttpServletRequestBuilder requestB = MockMvcRequestBuilders
                .post("/api/v1/auth/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonB);

        //then
        mockMvc.perform(requestB)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((result) -> Assertions.assertThat(result.getResolvedException().getClass()).isAssignableFrom(JoinDuplicateEmailException.class))
                .andReturn();
    }


}