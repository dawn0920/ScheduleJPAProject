package org.example.schedulejpaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulejpaproject.entity.Schedule;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PageResponseDto {
    private String title;
    private String content;
    private int commentNum;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String name;

    public PageResponseDto(Schedule schedule, int commentNum) {
        this.title = schedule.getTitle();
        this.content = schedule.getContents();
        this.commentNum = commentNum;
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.name = schedule.getUser().getName();
    }
}
