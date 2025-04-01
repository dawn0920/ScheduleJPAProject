package org.example.schedulejpaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private final String email;

    @NotNull
    private final String password;
}
