package org.example.schedulejpaproject.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final int id;
    private final String name;

    public LoginResponseDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
