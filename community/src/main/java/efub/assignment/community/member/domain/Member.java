package efub.assignment.community.member.domain;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static efub.assignment.community.member.domain.MemberStatus.REGISTERED;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false, length = 16)
    private String nickname;

    @Column(nullable = false, length = 60)
    private String university;

    @Column(nullable = false, length = 10)
    private Long studentNo;
    // String -> Long으로 수정
    // StudentId -> StudentNo로 수정

    @Enumerated(EnumType.STRING) // enum 타입
    private MemberStatus status;

    /* mappedBy : 연관관계의 주인 */
    /* cascade : 엔티티 삭제 시 연관된 엔티티의 처리 방식. */
    /* orphanRemoval : 고아 객체의 처리 방식 */
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder // 객체 생성
    public Member(String email, String encodedPassword, String nickname, String university, Long studentNo) {
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.nickname = nickname;
        this.university = university;
        this.studentNo = studentNo;
        this.status = MemberStatus.REGISTERED;
        // 조금 더 명확하게 MemberStatus.REGISTERED로 수정
    }

    //닉네임 수정하기
    public void updateMember(String nickname) {
        this.nickname = nickname;
    }

    //회원 탈퇴
    public void withdrawMember() {
        this.status = MemberStatus.UNREGISTERED;
    }
}