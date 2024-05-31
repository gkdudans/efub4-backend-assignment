package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.messageRoom.domain.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MessageResponseDto {
    private Long messageId;
    private Long senderId;
    private String message;
    private LocalDateTime createdDate;

    public static MessageResponseDto from(Message message){
        return new MessageResponseDto(
                message.getMessageId(),
                message.getSender().getMemberId(),
                message.getMessage(),
                message.getCreatedDate()
        );
    }
}
