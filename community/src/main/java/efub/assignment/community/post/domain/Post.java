package efub.assignment.community.post.domain;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id", updatable = false)
    private Board board;

    @Column(nullable = false)
    private Boolean anonymous;

    @Column(length = 50)
    private String content;

    /* mappedBy : 연관관계의 주인 */
    /* cascade : 엔티티 삭제 시 연관된 엔티티의 처리 방식. */
    /* orphanRemoval : 고아 객체의 처리 방식 */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(Member member, Board board, Boolean anonymous, String content) {
        this.member = member;
        this.board = board;
        this.anonymous = anonymous;
        this.content = content;
    }

    public void update(PostRequestDto requestDto, Member member, Board board) {
        this.member = member;
        this.board = board;
        this.anonymous = requestDto.getAnonymous();
        this.content = requestDto.getContent();
    }
}
