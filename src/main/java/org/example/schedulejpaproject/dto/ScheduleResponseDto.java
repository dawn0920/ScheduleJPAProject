package org.example.schedulejpaproject.dto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final int id;
    private final String title;
    private final String contents;

    public ScheduleResponseDto(int id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }
}
