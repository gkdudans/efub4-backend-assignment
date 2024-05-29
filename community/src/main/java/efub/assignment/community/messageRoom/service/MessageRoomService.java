package efub.assignment.community.messageRoom.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;
    public final PostService postService;
    public final MemberService memberService;

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
        return messageRoomRepository.save(messageRoom);
//        MessageRoom messageRoom = messageRoomRequestDto.toEntity(receiver, sender, post);
//        MessageRoom savedmessageRoom = messageRoomRepository.save(messageRoom);
//        return savedmessageRoom;
    }

    @Transactional(readOnly = true)
    public boolean ismessageRoom(Long receiverId, Long senderId){
        Member receiver = memberService.findMemberById(receiverId);
        Member sender = memberService.findMemberById(senderId);
        return messageRoomRepository.existsByReceiverAndSender(receiver, sender);
    }

//    @Transactional(readOnly = true)
//    public List<messageRoom> findAllByMemberId(Long memberId) {
//        Member findMember = memberService.findMemberById(memberId);
//        return messageRoomRepository.findAllByMember(findMember);
//    }

    @Transactional(readOnly = true)
    public MessageRoom findByReceiverIdAndSenderIdAndPostId(Long receiverId, Long senderId, Long postId) {
        return messageRoomRepository.findByReceiver_MemberIdAndSender_MemberIdAndPost_PostId(receiverId, senderId, postId);
    }

    public MessageRoom findMessageRoomById(Long messageRoomId) {
        return messageRoomRepository.findById(messageRoomId)
               .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 messageRoomId 입니다. messageRoomId=" + messageRoomId));
    }
}
