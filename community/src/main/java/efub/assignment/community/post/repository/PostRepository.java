package efub.assignment.community.post.repository;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBoard_BoardId(Long board_id);
}
