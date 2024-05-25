package efub.assignment.community.messageRoom.service;

import efub.assignment.community.alarm.service.AlarmService;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.MessageRoomListResponseDto;
import efub.assignment.community.messageRoom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
    private final PostService postService;
    private final MemberService memberService;
    private final AlarmService alarmService;

    public MessageRoom add(Long memberId, MessageRoomRequestDto messageRoomRequestDto) {
        Member receiver = memberService.findMemberById(memberId);
        Member sender = memberService.findMemberById(messageRoomRequestDto.getSenderId());
        Post post = postService.findPostById(messageRoomRequestDto.getPostId());
        MessageRoom messageRoom = MessageRoom.builder()
                .receiver(receiver)
                .sender(sender)
                .message(messageRoomRequestDto.getMessage())
                .post(post)
                .build();
        alarmService.createMessageRoomAlarm(); // 메시지룸 생성 시 알림

        return messageRoomRepository.save(messageRoom);
    }

    @Transactional(readOnly = true)
    public boolean ismessageRoom(Long receiverId, Long senderId){
        Member receiver = memberService.findMemberById(receiverId);
        Member sender = memberService.findMemberById(senderId);
        return messageRoomRepository.existsByReceiverAndSender(receiver, sender);
    }

    @Transactional(readOnly = true)
    public MessageRoom findByReceiverIdAndSenderIdAndPostId(Long receiverId, Long senderId, Long postId) {
        return messageRoomRepository.findByReceiver_MemberIdAndSender_MemberIdAndPost_PostId(receiverId, senderId, postId);
    }

    @Transactional(readOnly = true)
    public MessageRoom findMessageRoomById(Long messageRoomId) {
        return messageRoomRepository.findById(messageRoomId)
               .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 messageRoomId 입니다. messageRoomId=" + messageRoomId));
    }

    @Transactional(readOnly = true)
    public MessageRoomListResponseDto getMessageRoomList(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        List<MessageRoom> messageRoomList = messageRoomRepository.findAllByReceiverOrSender(member, member);
        return MessageRoomListResponseDto.of(member, messageRoomList);
    }
}
