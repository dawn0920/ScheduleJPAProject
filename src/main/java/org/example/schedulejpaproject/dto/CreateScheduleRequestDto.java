package org.example.schedulejpaproject.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {
    private final String title;
    private final String contents;
    private final String name;

    public CreateScheduleRequestDto(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }
}
