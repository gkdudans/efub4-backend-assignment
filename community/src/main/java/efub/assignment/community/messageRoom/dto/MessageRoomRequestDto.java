package efub.assignment.community.messageRoom.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomRequestDto {
    private Long senderId;

    public MessageRoomRequestDto(Long senderId){
        this.senderId = senderId;
    }

    @NotBlank(message = "쪽지가 시작된 글의 id는 필수입니다.")
    private Long postId;

    private String message;

    public MessageRoom toEntity(Member receiver, Member sender, Post post){
        return MessageRoom.builder()
                .receiver(receiver)
                .sender(sender)
                .message(message)
                .post(post)
                .build();
    }
}

