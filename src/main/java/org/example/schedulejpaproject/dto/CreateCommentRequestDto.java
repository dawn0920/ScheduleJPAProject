package org.example.schedulejpaproject.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {
    private final String content;

    public CreateCommentRequestDto(String content) {
        this.content = content;
    }
}
