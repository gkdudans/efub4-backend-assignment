package efub.assignment.community.messageRoom.repository;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    Boolean existsByReceiverAndSender(Member receiver, Member sender);
//    List<messageRoom> findAllByMember(Member member);
    MessageRoom findByReceiver_MemberIdAndSender_MemberIdAndPost_PostId(Long receiverId, Long senderId, Long postId);
}
