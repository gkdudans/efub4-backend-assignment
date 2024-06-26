package efub.assignment.community.post.controller;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.CommentRequestDto;
import efub.assignment.community.comment.dto.CommentResponseDto;
import efub.assignment.community.comment.dto.MemberInfoRequestDto;
import efub.assignment.community.comment.service.CommentHeartService;
import efub.assignment.community.comment.service.CommentService;
import efub.assignment.community.post.dto.HeartRequestDto;
import efub.assignment.community.post.dto.PostCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/posts/{postId}/comments")
public class PostCommentController {
    private final CommentService commentService;
    private final CommentHeartService commentHeartService;

    /* 게시글에 댓글 생성 */
    // ResponseEntity<>: @ResponseBody보다 HTTP 옵션에 유연함
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("boardId") Long boardId,
                                                            @PathVariable("postId") Long postId,
                                                            @RequestBody CommentRequestDto requestDto){
        Comment comment = commentService.saveComment(boardId, postId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommentResponseDto.of(comment));
    }

    /* 게시글 댓글 전체 조회 */
    @GetMapping
    public ResponseEntity<PostCommentResponseDto> getAllComment(@PathVariable("boardId") Long boardId,
                                                                @PathVariable("postId") Long postId){
        List<Comment> commentList = commentService.findPostCommentList(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(PostCommentResponseDto.of(postId, commentList));
    }
    /* 댓글 수정 */
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getAllComment(@PathVariable("commentId") Long commentId,
                                                            @RequestBody CommentRequestDto requestDto){
        Comment comment = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommentResponseDto.of(comment));
    }

    /* 댓글 삭제 */
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId,
                                @RequestParam("memberId") Long memberId){
        commentService.deleteComment(commentId, memberId);
        return "댓글이 삭제되었습니다.";
    }

    /* 댓글 좋아요 생성 */
    @PostMapping("/{commentId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentHeart(@PathVariable Long commentId,
                                     @RequestBody final MemberInfoRequestDto requestDto){
        commentHeartService.create(commentId, requestDto);
        return "좋아요를 눌렀습니다.";
    }

    /* 댓글 좋아요 삭제 */
    @DeleteMapping("/{commentId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCommentHeart(@PathVariable Long commentId,
                                     @RequestParam("memberId") Long memberId){
        commentHeartService.delete(commentId, memberId);
        return "좋아요를 취소했습니다.";
    }

}
