package efub.assignment.community.post.domain;

import efub.assignment.community.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_heart_id")
    private Long id;

    /* Post-Heart
    * 외래키의 주인이 Post이므로, 연관관계의 주인 Post */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "게시글은 필수로 입력해야 합니다.")
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    /* Post-Member */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "작성자는 필수로 입력해야 합니다.")
    @JoinColumn(name = "member_id", updatable = false)
    private Member writer;

    @Builder
    public PostHeart(Post post, Member member) {
        this.post = post;
        this.writer = member;
    }


}
