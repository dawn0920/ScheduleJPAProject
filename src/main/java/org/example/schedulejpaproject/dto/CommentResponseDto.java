package org.example.schedulejpaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulejpaproject.entity.Comment;
import org.example.schedulejpaproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private int id;
    private String content;
    private String userName;
    private LocalDateTime modified_at;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getName();
        this.modified_at = comment.getModifiedAt();
    }

    public CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getName(),
                comment.getModifiedAt()
        );
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getId(),comment.getContent(), comment.getUser().getName(), comment.getModifiedAt());
    }
}
