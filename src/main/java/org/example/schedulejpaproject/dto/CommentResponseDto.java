package org.example.schedulejpaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulejpaproject.entity.Comment;
import org.example.schedulejpaproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private String content;
    private String userName;
    private LocalDateTime modified_at;

    public CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getContent(),
                comment.getUser().getName(),
                comment.getModifiedAt()
        );
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getContent(), comment.getUser().getName(), comment.getModifiedAt());
    }
}
