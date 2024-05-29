package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MessageRoomListResponseDto {
    private Long memberId;
    private List<AllMessageRoomsResponseDto> messageRoomList;

    public static MessageRoomListResponseDto of(Member member, List<MessageRoom> messageRoomList) {
        List<AllMessageRoomsResponseDto> messageRooms = messageRoomList.stream()
                .map(messageRoom -> {
                    Message lastMessage = messageRoom.getLastMessage();
                    return AllMessageRoomsResponseDto.builder()
                            .messageRoomId(messageRoom.getMessageRoomId())
                            .lastMessage(lastMessage != null ? lastMessage.getMessage() : null)
                            .lastMessageCreatedDate(lastMessage != null ? lastMessage.getCreatedDate() : null)
                            .build();
                })
                .collect(Collectors.toList());

        return MessageRoomListResponseDto.builder()
                .memberId(member.getMemberId())
                .messageRoomList(messageRooms)
                .build();
    }

    @Getter
    @Builder
    public static class AllMessageRoomsResponseDto {
        private Long messageRoomId;
        private String lastMessage;
        private LocalDateTime lastMessageCreatedDate;
    }
}
