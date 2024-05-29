package efub.assignment.community.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomPermissionException extends RuntimeException{
    private final ErrorCode errorCode;
}