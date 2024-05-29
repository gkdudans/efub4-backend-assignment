package efub.assignment.community.messageRoom.service;

<<<<<<< HEAD
=======
import efub.assignment.community.exception.CustomException;
import efub.assignment.community.exception.ErrorCode;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
<<<<<<< HEAD
import efub.assignment.community.messageRoom.dto.MessageRequestDto;
=======
import efub.assignment.community.messageRoom.dto.MessageListResponseDto;
import efub.assignment.community.messageRoom.dto.MessageRequestDto;
import efub.assignment.community.messageRoom.dto.MessageRoomListResponseDto;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
import efub.assignment.community.messageRoom.repository.MessageRepository;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.stream.Collectors;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
<<<<<<< HEAD
    private final MessageRoomService messageRoomService;
    private final MemberService memberService;

    public List<Message> findMessageList(Long messageRoomId) {
        MessageRoom messageRoom = messageRoomService.findMessageRoomById(messageRoomId);
        return messageRepository.findAllByMessageRoom(messageRoom);
    }

    public Message createMessage(Long messageRoomId, MessageRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomService.findMessageRoomById(messageRoomId);
        Member sender = memberService.findMemberById(requestDto.getSenderId());

=======
    private final MessageRoomRepository messageRoomRepository;
    private final MessageRoomService messageRoomService;
    private final MemberService memberService;

    public Message createMessage(Long messageRoomId, MessageRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomService.findMessageRoomById(messageRoomId);
        Member sender = memberService.findMemberById(requestDto.getSenderId());
        if (sender!=messageRoom.getSender() && sender!=messageRoom.getReceiver()) {
            throw new CustomException(ErrorCode.PERMISSION_REJECTED_USER);
        }
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
        Message message = Message.builder()
                .sender(sender)
                .message(requestDto.getMessage())
                .messageRoom(messageRoom)
                .build();
        messageRepository.save(message);
        return message;
    }
<<<<<<< HEAD
=======

    @Transactional(readOnly = true)
    public MessageListResponseDto getMessageList(Long messageRoomId, Long memberId) {
        MessageRoom messageRoom = messageRoomService.findMessageRoomById(messageRoomId);
        Member member = memberService.findMemberById(memberId);
        List<Message> messageList = messageRepository.findAllByMessageRoom(messageRoom);
        return MessageListResponseDto.of(messageRoom, member, messageList);
    }
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
}
