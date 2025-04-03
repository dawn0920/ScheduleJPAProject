package org.example.schedulejpaproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.CommentResponseDto;
import org.example.schedulejpaproject.dto.CommentUpdateRequestDto;
import org.example.schedulejpaproject.dto.CreateCommentRequestDto;
import org.example.schedulejpaproject.dto.ScheduleResponseDto;
import org.example.schedulejpaproject.entity.Comment;
import org.example.schedulejpaproject.service.CommentService;
import org.example.schedulejpaproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final ScheduleService scheduleService;
    private final CommentService commentService;

    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentResponseDto> save (
            @PathVariable int scheduleId,
            @Valid @RequestBody CreateCommentRequestDto requestDto,
            HttpServletRequest request
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(scheduleId);
        HttpSession session = request.getSession(false);

        int userId;

        if(session == null || session.getAttribute("user") == null) {
            userId = 1;
        } else {
            userId = (int) session.getAttribute("user");
        }

        CommentResponseDto commentResponseDto =
                commentService.save(
                        userId,
                        scheduleId,
                        requestDto.getContent()
                );
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/comment/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> findAll(
            @PathVariable int scheduleId
    ) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAllByScheduleId(scheduleId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @PatchMapping("/comment/{scheduleId}/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment (
            @PathVariable int scheduleId,
            @PathVariable int commentId,
            @Valid @RequestBody CommentUpdateRequestDto requestDto
    ) {
        CommentResponseDto commentResponseDto = commentService.updateComment(scheduleId, commentId, requestDto);


        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{scheduleId}/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable int scheduleId,
            @PathVariable int commentId
    ) {
        commentService.delete(scheduleId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
