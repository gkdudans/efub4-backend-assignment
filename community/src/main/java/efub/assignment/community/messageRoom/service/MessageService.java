package efub.assignment.community.messageRoom.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.MessageRequestDto;
import efub.assignment.community.messageRoom.repository.MessageRepository;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageRoomService messageRoomService;
    private final MemberService memberService;

    public List<Message> findMessageList(Long messageRoomId) {
        MessageRoom messageRoom = messageRoomService.findMessageRoomById(messageRoomId);
        return messageRepository.findAllByMessageRoom(messageRoom);
    }

    public Message createMessage(Long messageRoomId, MessageRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomService.findMessageRoomById(messageRoomId);
        Member sender = memberService.findMemberById(requestDto.getSenderId());

        Message message = Message.builder()
                .sender(sender)
                .message(requestDto.getMessage())
                .messageRoom(messageRoom)
                .build();
        messageRepository.save(message);
        return message;
    }
}
