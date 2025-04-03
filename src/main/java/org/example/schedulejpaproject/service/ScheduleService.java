package org.example.schedulejpaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.ScheduleResponseDto;
import org.example.schedulejpaproject.dto.ScheduleUpdateRequestDto;
import org.example.schedulejpaproject.entity.Schedule;
import org.example.schedulejpaproject.entity.User;
import org.example.schedulejpaproject.repository.ScheduleRepository;
import org.example.schedulejpaproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public ScheduleResponseDto save(int userId, String title, String contents, String name) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Schedule schedule = new Schedule(title, contents, name);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getTitle(), schedule.getContents(), schedule.getName());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findById(int id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User writer = findSchedule.getUser();

        return new ScheduleResponseDto(findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getName());
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(int id, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("수정할 일정을 찾을 수 없습니다."));

        // 일정 수정
        schedule.update(requestDto.getTitle(), requestDto.getContents());

        // 응답
        return new ScheduleResponseDto(schedule);
    }

    public void delete(int id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
