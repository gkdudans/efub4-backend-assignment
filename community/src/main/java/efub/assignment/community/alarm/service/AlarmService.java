package efub.assignment.community.alarm.service;

import efub.assignment.community.alarm.domain.Alarm;
import efub.assignment.community.alarm.domain.AlarmType;
import efub.assignment.community.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;

    @Transactional
    public Alarm createCommentAlarm(String boardName, String commentContent) {
        String content = "댓글이 생성되었습니다: " + commentContent;
        Alarm alarm = Alarm.builder()
                .type(AlarmType.COMMENT)
                .boardName(boardName)
                .content(content)
                .createdDate(LocalDateTime.now())
                .build();
        return alarmRepository.save(alarm);

    }

    @Transactional
    public Alarm createMessageRoomAlarm() {
        String content = "쪽지방이 생성되었습니다";
        Alarm alarm = Alarm.builder()
                .type(AlarmType.MESSAGE_ROOM)
                .content(content)
                .createdDate(LocalDateTime.now())
                .build();
        return alarmRepository.save(alarm);
    }

    @Transactional(readOnly = true)
    public List<Alarm> getAllAlarms() {
        return alarmRepository.findAll();
    }
}
