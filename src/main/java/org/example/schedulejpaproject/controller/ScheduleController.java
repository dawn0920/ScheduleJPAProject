package org.example.schedulejpaproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.CreateScheduleRequestDto;
import org.example.schedulejpaproject.dto.ScheduleResponseDto;
import org.example.schedulejpaproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping //생성
    public ResponseEntity<ScheduleResponseDto> save(
            @RequestBody CreateScheduleRequestDto requestDto,
            HttpServletRequest request // 세션 사용을 위해 필요
    ) {
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null) {
            throw new RuntimeException("로그인이 해주세요.");
        }

        int userId = (int) session.getAttribute("user");

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(
                        userId,
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getName()
                );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }
}
