package efub.assignment.community.post.dto;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequestDto {
    @NotNull(message = "멤버 id는 필수입니다.")
    private Long memberId;

//    @NotNull(message = "게시판 id는 필수입니다.")
//    private Long boardId;

    @NotNull(message = "작성자 표시 방법은 필수입니다.")
    private Boolean anonymous;

    private String content;

    public Post toEntity(Member member, Board board) {
        return Post.builder()
               .member(member)
               .board(board)
               .anonymous(anonymous)
               .content(content)
               .build();
    }
}
