package efub.assignment.community.global.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /**
     * 현재 인증된 사용자 kakaoId 반환 메서드
     * 만약 인증 정보가 없는 경우, null 반환
     */
    public static Long getCurrentUserKakaoId() {
        // SecurityContext에서 현재 인증 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증 정보가 없거나 사용자 ID가 없는 경우, null 반환
        if (authentication == null || authentication.getName() == null) {
            return null;
        }

        try {
            // 인증된 사용자의 kakaoId를 Long 타입으로 변환하여 반환
            return Long.valueOf(authentication.getName());
        } catch (NumberFormatException e) {
            // kakaoId가 올바르지 않은 형식이면 null 반환
            return null;
        }
    }
}
