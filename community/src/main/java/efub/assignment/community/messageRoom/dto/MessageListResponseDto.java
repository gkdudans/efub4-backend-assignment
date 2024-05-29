package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageListResponseDto {
    private Long messageRoomId;
    private Long otherMemberId;
    private List<AllMessagesResponseDto> messages;

    public static MessageListResponseDto of(MessageRoom messageRoom, Member member, List<Message> messageList) {
        Member otherMember = messageRoom.getReceiver().equals(member) ? messageRoom.getSender() : messageRoom.getReceiver();
        List<AllMessagesResponseDto> messages = messageList.stream()
                .map(message -> AllMessagesResponseDto.builder()
                        .message(message.getMessage())
                        .createdDate(message.getCreatedDate())
                        .sentByMe(message.getSender().equals(member))
                        .build())
                .collect(Collectors.toList());

        return MessageListResponseDto.builder()
                .messageRoomId(messageRoom.getMessageRoomId())
                .otherMemberId(otherMember.getMemberId())
                .messages(messages)
                .build();
    }

    @Getter
    @Builder
    public static class AllMessagesResponseDto {
        private String message;
        private LocalDateTime createdDate;
        private boolean sentByMe;
    }
}