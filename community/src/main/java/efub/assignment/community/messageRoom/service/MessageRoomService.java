package efub.assignment.community.messageRoom.service;

<<<<<<< HEAD
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.MessageRoom;
=======
import efub.assignment.community.alarm.service.AlarmService;
import efub.assignment.community.exception.CustomException;
import efub.assignment.community.exception.ErrorCode;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.MessageRoomListResponseDto;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
import efub.assignment.community.messageRoom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
=======
import java.util.List;
import java.util.stream.Collectors;

>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
@Service
@Transactional
@RequiredArgsConstructor
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
<<<<<<< HEAD
    public final PostService postService;
    public final MemberService memberService;
=======
    private final PostService postService;
    private final MemberService memberService;
    private final AlarmService alarmService;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c

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
<<<<<<< HEAD
        return messageRoomRepository.save(messageRoom);
//        MessageRoom messageRoom = messageRoomRequestDto.toEntity(receiver, sender, post);
//        MessageRoom savedmessageRoom = messageRoomRepository.save(messageRoom);
//        return savedmessageRoom;
=======
        alarmService.createMessageRoomAlarm(); // 메시지룸 생성 시 알림

        return messageRoomRepository.save(messageRoom);
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
    }

    @Transactional(readOnly = true)
    public boolean ismessageRoom(Long receiverId, Long senderId){
        Member receiver = memberService.findMemberById(receiverId);
        Member sender = memberService.findMemberById(senderId);
        return messageRoomRepository.existsByReceiverAndSender(receiver, sender);
    }

<<<<<<< HEAD
//    @Transactional(readOnly = true)
//    public List<messageRoom> findAllByMemberId(Long memberId) {
//        Member findMember = memberService.findMemberById(memberId);
//        return messageRoomRepository.findAllByMember(findMember);
//    }

=======
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
    @Transactional(readOnly = true)
    public MessageRoom findByReceiverIdAndSenderIdAndPostId(Long receiverId, Long senderId, Long postId) {
        return messageRoomRepository.findByReceiver_MemberIdAndSender_MemberIdAndPost_PostId(receiverId, senderId, postId);
    }

<<<<<<< HEAD
=======
    @Transactional(readOnly = true)
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
    public MessageRoom findMessageRoomById(Long messageRoomId) {
        return messageRoomRepository.findById(messageRoomId)
               .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 messageRoomId 입니다. messageRoomId=" + messageRoomId));
    }
<<<<<<< HEAD
=======

    @Transactional(readOnly = true)
    public MessageRoomListResponseDto getMessageRoomList(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        List<MessageRoom> messageRoomList = messageRoomRepository.findAllByReceiverOrSender(member, member);
        return MessageRoomListResponseDto.of(member, messageRoomList);
    }

    public void deleteMessageRoom(Long messageRoomId, Long memberId) {
        MessageRoom messageRoom = findMessageRoomById(messageRoomId);
        if(memberId!=messageRoom.getReceiver().getMemberId() && memberId!=messageRoom.getSender().getMemberId()) {
            throw new CustomException(ErrorCode.PERMISSION_REJECTED_USER);
        }
        messageRoomRepository.delete(messageRoom);
    }
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
}
