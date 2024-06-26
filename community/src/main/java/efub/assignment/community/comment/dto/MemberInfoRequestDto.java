package efub.assignment.community.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoRequestDto {
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    private Long memberId;

    public MemberInfoRequestDto(Long memberId) {
        this.memberId = memberId;
    }
}
