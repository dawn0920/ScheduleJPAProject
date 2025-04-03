package org.example.schedulejpaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.CommentResponseDto;
import org.example.schedulejpaproject.dto.CommentUpdateRequestDto;
import org.example.schedulejpaproject.dto.ScheduleResponseDto;
import org.example.schedulejpaproject.entity.Comment;
import org.example.schedulejpaproject.entity.Schedule;
import org.example.schedulejpaproject.entity.User;
import org.example.schedulejpaproject.repository.CommentRepository;
import org.example.schedulejpaproject.repository.ScheduleRepository;
import org.example.schedulejpaproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public CommentResponseDto save(int userId, int scheduleId, String content) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Schedule findSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        Comment comment = new Comment(content, findUser, findSchedule);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getUser().getName(), comment.getModifiedAt());
    }

    // 조회
    public List<CommentResponseDto> findAllByScheduleId(int scheduleId) { // ✅ 반환 타입 수정
        return commentRepository.findByScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }


    // 수정
    @Transactional
    public CommentResponseDto updateComment(int scheduleId, int commentId, CommentUpdateRequestDto requestDto) {
        Schedule findSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        comment.update(requestDto.getContent());

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    // 삭제
    public void delete(int scheduleId, int commentId) {
        Schedule findSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

        commentRepository.delete(findComment);
    }
}
