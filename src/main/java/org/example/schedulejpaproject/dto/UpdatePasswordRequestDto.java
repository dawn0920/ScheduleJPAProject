package org.example.schedulejpaproject.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    private String oldPassword;

    @Pattern(
            regexp = "^(?=.*[!@#$%^*(),.?/\":{}|<>])[a-zA-Z0-9!@#$%^*(),.?/\":{}|<>]{8,16}$",
            message = "비밀번호는 8 ~ 16자리 사이로, 특수문자를 하나 이상 포함시켜서 입력해주세요."
    )
    private String newPassword;

}
