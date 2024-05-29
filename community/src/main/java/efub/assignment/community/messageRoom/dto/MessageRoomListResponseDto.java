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
