package efub.assignment.community.messageRoom.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageRoom_id", updatable = false)
    private Long messageRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messageList = new ArrayList<>();

    @Builder
    public MessageRoom(Member receiver, Member sender, String message, Post post){
        this.receiver = receiver;
        this.sender = sender;
        firstMessage(message);
        this.post = post;
    }

    public void firstMessage(String message){
        Message firstMessage = Message.builder()
                .sender(sender)
                .message(message)
                .messageRoom(this)
                .build();
        this.messageList.add(firstMessage);
    }

    public String getmessage() {
        if (!messageList.isEmpty()) {
            return messageList.get(0).getMessage();
        }
        return null;
    }
<<<<<<< HEAD
=======

    public Message getLastMessage() {
        if (messageList.isEmpty()) {
            return null;
        }
        return messageList.get(messageList.size() - 1);
    }
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
}
