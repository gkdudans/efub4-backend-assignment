package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRequestDto {
    private String message;
    private Long senderId; // senderId로 변경

    public Message toEntity(MessageRoom messageRoom){
        return Message.builder()
                .messageRoom(messageRoom)
                .message(message)
                .sender(messageRoom.getSender())
                .build();
    }
}
