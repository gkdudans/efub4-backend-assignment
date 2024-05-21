package efub.assignment.community.post.controller;

import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.AllPostResponseDto;
import efub.assignment.community.post.dto.HeartRequestDto;
import efub.assignment.community.post.dto.PostResponseDto;
import efub.assignment.community.post.dto.PostRequestDto;
import efub.assignment.community.post.service.PostHeartService;
import efub.assignment.community.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/posts")
public class PostController {
    private final PostService postService;
    private final PostHeartService postHeartService;

    /* 포스트 생성 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto createPost(@PathVariable("boardId") Long boardId,
                                      @RequestBody @Valid final PostRequestDto requestDto) {
        Post post = postService.createNewPost(boardId, requestDto);
        return PostResponseDto.from(post);
    }

    /* 포스트 조회 */
    @GetMapping
    public AllPostResponseDto getAllPostsById(@PathVariable("boardId") Long boardId) {
        List<PostResponseDto> list = new ArrayList<>();
        List<Post> posts = postService.findAllByBoard_BoardId(boardId);
        posts.forEach(post -> {
            PostResponseDto responseDto = PostResponseDto.from(post);
            list.add(responseDto);
        });
        long count = postService.countAllPosts(boardId);
        return new AllPostResponseDto(list, count);
    }

    /* 포스트 1개 조회 */
    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        Post post = postService.findPostById(postId);
        return PostResponseDto.from(post);
    }

    /* 포스트 수정 */
    @PatchMapping("/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId,
                                      @RequestBody @Valid final PostRequestDto requestDto) {
        Long post_Id = postService.updatePost(postId, requestDto);
        Post post = postService.findPostById(post_Id);
        return PostResponseDto.from(post);
    }

    /* 포스트 삭제 */
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId,
                           @RequestParam("memberId") Long memberId) {
        postService.deletePost(postId, memberId);
        return "포스트가 삭제되었습니다.";
    }

    /* 포스트 좋아요 생성 */
    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createHeart(@PathVariable Long postId,
                              @RequestBody final HeartRequestDto requestDto) {
        postHeartService.create(postId, requestDto.getMemberId());
        return "좋아요를 눌렀습니다.";
    }

    /* 포스트 좋아요 삭제 */
    @DeleteMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteHeart(@PathVariable Long postId,
                              @RequestParam("memberId") Long memberId) {
        postHeartService.delete(postId, memberId);
        return "좋아요가 삭제되었습니다.";
    }
}
