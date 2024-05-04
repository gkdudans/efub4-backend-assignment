package efub.assignment.community.comment.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.CommentRequestDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.exception.CustomDeleteException;
import efub.assignment.community.exception.ErrorCode;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;
    private final BoardService boardService;
    private final PostService postService;
    private final CommentRepository commentRepository;

    public Comment saveComment(Long boardId, Long postId, CommentRequestDto requestDto) {
        Member writer = memberService.findMemberById(requestDto.getMemberId());
        Board board = boardService.findBoardById(boardId);
        Post post = postService.findPostById(postId);

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .writer(writer)
                .board(board)
                .post(post)
                .build();
        commentRepository.save(comment);

        return comment;
    }

    public List<Comment> findPostCommentList(Long boardId, Long postId) {
        Board board = boardService.findBoardById(boardId);
        Post post = postService.findPostById(postId);
        return commentRepository.findAllByBoardAndPost(board, post);
    }

    public List<Comment> findMemberCommentList(Member writer) {
        return commentRepository.findAllByWriter(writer);
    }

    public Comment updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Comment를 찾을 수 없습니다. id=" + commentId));
        Member writer = memberService.findMemberById(requestDto.getMemberId());
        if (!comment.getWriter().equals(writer)) {
            throw new CustomDeleteException(ErrorCode.PERMISSION_REJECTED_USER);
        }
        comment.update(requestDto.getContent());
        return comment;
    }

    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Comment를 찾을 수 없습니다. id=" + commentId));
        if (memberId!=comment.getWriter().getMemberId()) {
            throw new CustomDeleteException(ErrorCode.PERMISSION_REJECTED_USER);
        }
        commentRepository.delete(comment);
    }
}
