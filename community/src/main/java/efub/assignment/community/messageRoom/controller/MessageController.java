package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.messageRoom.domain.Message;
<<<<<<< HEAD
import efub.assignment.community.messageRoom.dto.MessageRequestDto;
import efub.assignment.community.messageRoom.dto.MessageResponseDto;
=======
import efub.assignment.community.messageRoom.dto.MessageListResponseDto;
import efub.assignment.community.messageRoom.dto.MessageRequestDto;
import efub.assignment.community.messageRoom.dto.MessageResponseDto;
import efub.assignment.community.messageRoom.dto.MessageRoomListResponseDto;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
import efub.assignment.community.messageRoom.service.MessageRoomService;
import efub.assignment.community.messageRoom.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messageRooms/{messageRoomId}/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final MessageRoomService messageRoomService;

<<<<<<< HEAD
=======
    /* 메시지 생성 */
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponseDto createMessage(@PathVariable("messageRoomId") Long messageRoomId,
                                            @RequestBody final MessageRequestDto requestDto) {
        Message message = messageService.createMessage(messageRoomId, requestDto);
        return MessageResponseDto.from(message);
    }

<<<<<<< HEAD
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<MessageResponseDto>> getAllMessage(@PathVariable("messageRoomId") Long messageRoomId, @PathVariable Long memberId){
        List<Message> messageList = messageService.findMessageList(messageRoomId);
        List<MessageResponseDto> responseDtoList = messageList.stream()
                .map(MessageResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
=======
    /* 메시지룸 전체 메시지 조회 */
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MessageListResponseDto getMessageList(@PathVariable("messageRoomId") Long messageRoomId,
                                                 @PathVariable("memberId") Long memberId) {
        return messageService.getMessageList(messageRoomId, memberId);
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
    }
}
