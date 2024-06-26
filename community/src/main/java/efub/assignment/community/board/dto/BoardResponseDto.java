package efub.assignment.community.board.dto;

import efub.assignment.community.board.domain.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String boardName;
    private String description;
    private String notice;
    private String ownerNickname;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static BoardResponseDto from(Board board, String ownerNickname) {
        return new BoardResponseDto(
                board.getBoardId(),
                board.getBoardName(),
                board.getDescription(),
                board.getNotice(),
                ownerNickname,
                board.getCreatedDate(),
                board.getModifiedDate()
        );
    }

}
