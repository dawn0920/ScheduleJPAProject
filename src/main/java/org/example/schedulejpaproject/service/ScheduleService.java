package org.example.schedulejpaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.ScheduleResponseDto;
import org.example.schedulejpaproject.entity.Schedule;
import org.example.schedulejpaproject.entity.User;
import org.example.schedulejpaproject.repository.ScheduleRepository;
import org.example.schedulejpaproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public ScheduleResponseDto save(String title, String contents, String name) {

        User findUser = userRepository.findUserByNameOrElseThrow(name);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }
}
