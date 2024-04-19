package efub.assignment.community.post.dto;

import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private Boolean anonymous;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static PostResponseDto from(Post post){
        return new PostResponseDto(
                post.getPostId(),
                post.getAnonymous(),
                post.getContent(),
                post.getCreatedDate(),
                post.getModifiedDate()
        );
    }

}
