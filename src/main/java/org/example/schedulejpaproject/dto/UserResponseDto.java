package org.example.schedulejpaproject.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final int id;
    private final String name;
    private final String email;

    public UserResponseDto(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
