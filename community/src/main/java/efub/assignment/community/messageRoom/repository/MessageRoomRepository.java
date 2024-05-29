package efub.assignment.community.messageRoom.repository;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    Boolean existsByReceiverAndSender(Member receiver, Member sender);
    MessageRoom findByReceiver_MemberIdAndSender_MemberIdAndPost_PostId(Long receiverId, Long senderId, Long postId);
    List<MessageRoom> findAllByReceiverOrSender(Member receiver, Member sender);
}
