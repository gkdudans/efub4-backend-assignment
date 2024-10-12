package efub.assignment.community.comment.service;

import efub.assignment.community.alarm.service.AlarmService;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.CommentRequestDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.exception.CustomException;
import efub.assignment.community.exception.ErrorCode;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;
    private final BoardService boardService;
    private final PostService postService;
    private final AlarmService alarmService;
    private final CommentRepository commentRepository;

    /* 댓글 생성 */
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
        alarmService.createCommentAlarm(post.getBoard().getBoardName(), requestDto.getContent()); // 댓글 생성 시 알림

        return comment;
    }

    /* 게시글의 댓글 목록 조회 */
    public List<Comment> findPostCommentList(Long boardId, Long postId) {
        Board board = boardService.findBoardById(boardId);
        Post post = postService.findPostById(postId);
        return commentRepository.findAllByBoardAndPost(board, post);
    }

    /* 작성자의 댓글 목록 조회 */
    public List<Comment> findMemberCommentList(Member writer) {
        return commentRepository.findAllByWriter(writer);
    }

    /* 댓글 수정 */
    public Comment updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Comment를 찾을 수 없습니다. id=" + commentId));
        Member writer = memberService.findMemberById(requestDto.getMemberId());
        if (!comment.getWriter().equals(writer)) {
            throw new CustomException(ErrorCode.PERMISSION_REJECTED_USER);
        }
        comment.update(requestDto.getContent());
        return comment;
    }

    /* 댓글 삭제 */
    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Comment를 찾을 수 없습니다. id=" + commentId));
        if (memberId!=comment.getWriter().getMemberId()) {
            throw new CustomException(ErrorCode.PERMISSION_REJECTED_USER);
        }
        commentRepository.delete(comment);
    }

    /* commentId 조회 */
    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
    }
}
