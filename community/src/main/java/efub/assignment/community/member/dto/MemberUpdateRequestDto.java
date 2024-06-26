package efub.assignment.community.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {
    @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
    private String nickname;

    public MemberUpdateRequestDto(String nickname) {
        this.nickname = nickname;
    } // nickname 필드 하나만을 사용함 -> @Builder 대신 생성자 코드 작성
}
