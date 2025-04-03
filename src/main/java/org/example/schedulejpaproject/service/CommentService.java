package org.example.schedulejpaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.CommentResponseDto;
import org.example.schedulejpaproject.entity.Comment;
import org.example.schedulejpaproject.entity.Schedule;
import org.example.schedulejpaproject.entity.User;
import org.example.schedulejpaproject.repository.CommentRepository;
import org.example.schedulejpaproject.repository.ScheduleRepository;
import org.example.schedulejpaproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 수정

    // 삭제
}
