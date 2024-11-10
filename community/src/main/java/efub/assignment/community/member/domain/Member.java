package efub.assignment.community.member.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.comment.domain.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Column(name = "kakao_id", updatable = false, unique = true)
    @NotNull
    private Long kakaoId;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "password")
    private String encodedPassword;

    @Column(nullable = false, length = 16)
    private String nickname;

    @Column(name = "university", length = 60)
    private String university;

    @Column(name = "studentNo", length = 10)
    private String studentNo;
    // StudentId -> StudentNo로 수정
    // Long -> String으로 수정

    @Enumerated(EnumType.STRING) // enum 타입
    private MemberStatus status;

    /* mappedBy : 연관관계의 주인 */
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder // 객체 생성
    public Member(Long kakaoId, String encodedPassword, String nickname) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.encodedPassword = encodedPassword;
        this.status = MemberStatus.REGISTERED;
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