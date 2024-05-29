package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomStatusResponseDto {
    private Long messageRoomId;
    private Long receiverId;
    private Long senderId;
    private String message;
    private String status;
    private LocalDateTime createdDate;

    @Builder
    public MessageRoomStatusResponseDto(MessageRoom messageRoom, Boolean ismessageRoom, Member receiver, Member sender) {
        this.messageRoomId = messageRoom.getMessageRoomId();
        this.receiverId = receiver.getMemberId();
        this.senderId = sender.getMemberId();
        this.message = messageRoom.getmessage();
        this.createdDate = messageRoom.getCreatedDate();
        if (!ismessageRoom) {
            this.status = "messageRoom not exist";
        } else {
            this.status = "messageRoom exist";
        }
    }
}
