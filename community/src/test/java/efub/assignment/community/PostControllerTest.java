package efub.assignment.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.assignment.community.exception.ErrorCode;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.dto.PostRequestDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc  // MockMvc 자동 구성
@Sql(scripts = "/data.sql") // 테스트 데이터 파일
@ActiveProfiles("test") // 활성화된 프로파일 "test"로 설정
@ContextConfiguration(classes = CommunityApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml") // 프로퍼티 파일 지정
public class PostControllerTest {
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

    /*  포스트 생성: 테스트 통과 */
    @Test
    @DisplayName("createPost : 포스트 생성 성공")
    public void createPost_성공() throws Exception{
        /* given */
        final String url = "/boards/1/posts";
        final Long memberId = 1L;  // data.sql에서 미리 생성
        final Boolean anonymous = false;
        final String content = "테스트용 포스트 생성입니다.";
        final PostRequestDto requestDto = createDefaultPostRequestDto(memberId, anonymous, content);

        /* when */
        final String requestBody = objectMapper.writeValueAsString(requestDto);

        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        /* then */
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postId").isNotEmpty())
                .andExpect(jsonPath("$.anonymous").value(anonymous))
                .andExpect(jsonPath("$.content").value(content));
    }

    /*  포스트 1개 조회: 테스트 실패 */
    @Test
    @DisplayName("getPost : 유효하지 않은 postId로 인해 조회 실패 테스트")
    public void getPost_실패() throws Exception {
        /* given */
        final String url = "/boards/{boardId}/posts/{postId}";
        final Long boardId = 1L;
        final Long postId = 999L;  // 존재하지 않는 postId

        /* when */
        ResultActions resultActions = mockMvc.perform(get(url, boardId, postId)
                .contentType(MediaType.APPLICATION_JSON));

        /* then */
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.boardId").value(boardId))
                .andExpect(jsonPath("$.postId").value(postId));
    }

    private PostRequestDto createDefaultPostRequestDto(Long memberId, Boolean anonymous, String content){
        return new PostRequestDto(memberId, anonymous, content);
    }

}
