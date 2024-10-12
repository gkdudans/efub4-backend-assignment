package efub.assignment.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc  // MockMvc 자동 구성
@Sql(scripts = "/data.sql") // 테스트 데이터 파일
@ActiveProfiles("test") // 활성화된 프로파일 "test"로 설정
@ContextConfiguration(classes = CommunityApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml") // 프로퍼티 파일 지정
public class MemberControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역직렬화

    @Autowired
    protected MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    /*  회원가입 : 테스트 통과 */
    @Test
    @DisplayName("createMember : 회원가입 성공")
    public void createMember_성공() throws Exception{
        /* given */
        final String url = "/members";
        final String email = "chrimhy@ewhain.net";
        final String password = "efub1234!!";
        final String nickname = "gkdudans";
        final String university = "이화여자대학교";
        final String studentNo = "2171064";
        final SignUpRequestDto requestDto = createDefaultSignUpRequestDto(email, password, nickname, university, studentNo);

        /* when */
        final String requestBody = objectMapper.writeValueAsString(requestDto);

        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        /* then */
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").isNotEmpty())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.nickname").value(nickname));

    }

    /*  회원가입 : 테스트 실패 */
    @Test
    @DisplayName("createMember : 이메일 형식에 의한 회원가입 실패 테스트")
    public void createMember_실패() throws Exception{
        /* given */
        final String url = "/members";
        final String email = "emailtest";
        final String password = "efub1234!!";
        final String nickname = "gkdudans";
        final String university = "이화여자대학교";
        final String studentNo = "2171064";
        final SignUpRequestDto requestDto = createDefaultSignUpRequestDto(email, password, nickname, university, studentNo);

        /* when */
        final String requestBody = objectMapper.writeValueAsString(requestDto);

        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        /* then */
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").isNotEmpty())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.nickname").value(nickname));
    }

    private SignUpRequestDto createDefaultSignUpRequestDto(String email,String password, String nickname, String university, String studentNo){
        return SignUpRequestDto.builder()
                .email(email)
                .encodedPassword(password)
                .nickname(nickname)
                .university(university)
                .studentNo(studentNo)
                .build();
    }
}
