package efub.assignment.community.alarm.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id", updatable = false)
    private Long alarmId;

    @Enumerated(EnumType.STRING)
    private AlarmType type;

    private String boardName;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public Alarm(AlarmType type, String boardName, String content, LocalDateTime createdDate) {
        this.type = type;
        this.boardName = boardName;
        this.content = content;
        this.createdDate = createdDate;
    }
}
