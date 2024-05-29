package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.dto.MessageRequestDto;
import efub.assignment.community.messageRoom.dto.MessageResponseDto;
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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponseDto createMessage(@PathVariable("messageRoomId") Long messageRoomId,
                                            @RequestBody final MessageRequestDto requestDto) {
        Message message = messageService.createMessage(messageRoomId, requestDto);
        return MessageResponseDto.from(message);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<MessageResponseDto>> getAllMessage(@PathVariable("messageRoomId") Long messageRoomId, @PathVariable Long memberId){
        List<Message> messageList = messageService.findMessageList(messageRoomId);
        List<MessageResponseDto> responseDtoList = messageList.stream()
                .map(MessageResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }
}
