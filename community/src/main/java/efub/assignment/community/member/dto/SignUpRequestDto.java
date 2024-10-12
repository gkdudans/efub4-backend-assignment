package efub.assignment.community.member.dto;

import efub.assignment.community.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    @NotBlank(message = "이메일은 필수입니다.")  // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.") // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
            message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
    private String encodedPassword;

    @NotBlank(message = "닉네임은 필수입니다. ") // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    private String nickname;

    @NotBlank(message = "학교 이름은 필수입니다. ") // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    private String university;

    @NotNull(message = "학번은 필수입니다.") // @NotBlank -> @NotNull로 수정
    private String studentNo;

    @Builder
    public SignUpRequestDto(String email, String encodedPassword, String nickname, String university, String studentNo){
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.nickname = nickname;
        this.university = university;
        this.studentNo = studentNo;
    }

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(this.email)
                .encodedPassword(this.encodedPassword)
                .nickname(this.nickname)
                .university(this.university)
                .studentNo(this.studentNo)
                .build();
    }
}
