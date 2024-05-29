package efub.assignment.community.post.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.exception.CustomPermissionException;
import efub.assignment.community.exception.ErrorCode;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostRequestDto;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    public Post createNewPost(Long boardId, PostRequestDto requestDto) {
        Member member = memberService.findMemberById(requestDto.getMemberId());
        Board board = boardService.findBoardById(boardId);
        Post post = requestDto.toEntity(member, board);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByBoard_BoardId(Long boardId) {
        List<Post> posts = postRepository.findAllByBoard_BoardId(boardId);
        return posts;
    }

    @Transactional(readOnly = true)
    public long countAllPosts(Long boardId){
        List<Post> posts = postRepository.findAllByBoard_BoardId(boardId);
        return posts.size();
    }

    @Transactional
    public Post findPostById(Long PostId){
        Post post = postRepository.findById(PostId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 Post가 존재하지 않습니다."));
        return post;
    }

    public Long updatePost(Long PostId, PostRequestDto requestDto) {
        Post post = findPostById(PostId);
        Member member = memberService.findMemberById(requestDto.getMemberId());
        Board board = boardService.findBoardById(post.getBoard().getBoardId());
        post.update(requestDto, member, board);
        return post.getPostId();
    }

    public void deletePost(Long postId, Long memberId){
        Post post = findPostById(postId);
        if(memberId!=post.getMember().getMemberId()){
            throw new CustomPermissionException(ErrorCode.PERMISSION_REJECTED_USER);
        }
        postRepository.delete(post);
    }
}
