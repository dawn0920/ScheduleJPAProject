package org.example.schedulejpaproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.CreateScheduleRequestDto;
import org.example.schedulejpaproject.dto.ScheduleResponseDto;
import org.example.schedulejpaproject.dto.ScheduleUpdateRequestDto;
import org.example.schedulejpaproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/create") //생성
    public ResponseEntity<ScheduleResponseDto> save(
            @RequestBody CreateScheduleRequestDto requestDto,
            HttpServletRequest request // 세션 사용을 위해 필요
    ) {
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null) {
            throw new RuntimeException("로그인 해주세요.");
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

    @GetMapping // 조회
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}") // 단건 조회
    public ResponseEntity<ScheduleResponseDto> findById(
            @PathVariable int id
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule (
            @PathVariable int id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto);


        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id
    ) {
        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
