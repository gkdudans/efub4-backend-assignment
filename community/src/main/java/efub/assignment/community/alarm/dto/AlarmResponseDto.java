package efub.assignment.community.alarm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import efub.assignment.community.alarm.domain.Alarm;
import efub.assignment.community.alarm.domain.AlarmType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmResponseDto {
    private Long alarmId;
    private AlarmType type;
    private String boardName;
    private String content;
    private LocalDateTime createdDate;

    public static AlarmResponseDto from(Alarm alarm) {
        return AlarmResponseDto.builder()
                .alarmId(alarm.getAlarmId())
                .type(alarm.getType())
                .boardName(alarm.getType() == AlarmType.COMMENT ? alarm.getBoardName() : null)
                // 메시지룸 생성 시 boardName 응답에 포함되지 않도록 수정
                .content(alarm.getContent())
                .createdDate(alarm.getCreatedDate())
                .build();
    }
}
