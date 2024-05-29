package efub.assignment.community.messageRoom.repository;

<<<<<<< HEAD
=======
import efub.assignment.community.member.domain.Member;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
import efub.assignment.community.messageRoom.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByMessageRoom(MessageRoom messageRoom);
}
