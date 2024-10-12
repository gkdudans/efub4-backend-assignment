package efub.assignment.community.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ENTITY_NOT_FOUND(404, "요청하신 ID와 일치하는 객체가 존재하지 않습니다."),
    PERMISSION_REJECTED_USER(403, "권한이 없는 사용자입니다."),
    ALREADY_LIKED(409, "이미 좋아요를 누르셨습니다."),
    POST_NOT_FOUND(404, "포스트를 찾을 수 없습니다.");

    private final int status;
    private final String message;
}
