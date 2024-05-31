package efub.assignment.community.alarm.controller;

import efub.assignment.community.alarm.domain.Alarm;
import efub.assignment.community.alarm.domain.AlarmType;
import efub.assignment.community.alarm.dto.AlarmResponseDto;
import efub.assignment.community.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    /* 알림 목록 조회 */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlarmResponseDto> getAllAlarms() {
        List<Alarm> alarms = alarmService.getAllAlarms();
        return alarms.stream()
                .map(AlarmResponseDto::from)
                .collect(Collectors.toList());
    }
}
