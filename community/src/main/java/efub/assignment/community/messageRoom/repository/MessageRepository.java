package efub.assignment.community.messageRoom.repository;

import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByMessageRoom(MessageRoom messageRoom);
}
