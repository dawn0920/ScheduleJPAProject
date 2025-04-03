package org.example.schedulejpaproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.example.schedulejpaproject.entity.Schedule;

@Getter
public class ScheduleResponseDto {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) // 기본값(0)일 경우 제외
    private int id;
    private final String title;
    private final String contents;
    private String name;

    public ScheduleResponseDto(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }

    public ScheduleResponseDto(int id, String title, String contents, String name) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getName());
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.name = schedule.getName();
    }
}
