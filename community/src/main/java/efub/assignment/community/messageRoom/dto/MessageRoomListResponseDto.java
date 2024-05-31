<<<<<<< HEAD
//package efub.assignment.community.messageRoomroom.dto;
//
//import efub.assignment.community.messageRoomroom.domain.messageRoom;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import org.apache.logging.log4j.message.Message;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@Builder
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//public class messageRoomListResponseDto {
//    private List<messageRoomStatusResponseDto> messageRoomStatusResponseDtoList;
//    private Long count;
//
//    public static messageRoomListResponseDto of(List<messageRoom> messageRooms, List<Message> messages) {
//        List<messageRoomStatusResponseDto> messageRoomStatusResponseDtoList = new ArrayList<>();
//
//        messageRooms.stream().forEach(messageRoom -> {
//            Message latestMessage = messages.stream()
//                    .filter(message -> message.getmessageRoom().equals(messageRoom))
//                    .sorted((m1, m2) -> m2.getCreatedAt().compareTo(m1.getCreatedAt()))
//                    .findFirst()
//                    .orElse(null);
//
//            messageRoomStatusResponseDto dto = messageRoomStatusResponseDto.of(messageRoom, latestMessage);
//            messageRoomStatusResponseDtoList.add(dto);
//        });
//
//        return messageRoomListResponseDto.builder()
//                .messageRoomStatusResponseDtoList(messageRoomStatusResponseDtoList)
//                .count((long) messageRoomStatusResponseDtoList.size())
//                .build();
//    }
//}
=======
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
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
