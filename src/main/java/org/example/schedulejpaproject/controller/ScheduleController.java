package org.example.schedulejpaproject.controller;

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
            @RequestBody CreateScheduleRequestDto requestDto
            ) {
        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getName()
                );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }
}
