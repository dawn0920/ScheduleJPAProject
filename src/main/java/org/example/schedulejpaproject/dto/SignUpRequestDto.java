package org.example.schedulejpaproject.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    @Size(max = 10, message = "이름은 10글자 내로 작성해주세요. ")
    private String name;

    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "유효한 이메일 주소를 입력하세요."
    )
    private String email;

    @Pattern(
            regexp = "^(?=.*[!@#$%^*(),.?/\":{}|<>])[a-zA-Z0-9!@#$%^*(),.?/\":{}|<>]{8,16}$",
            message = "비밀번호는 8 ~ 16자리 사이로, 특수문자를 하나 이상 포함시켜서 입력해주세요."
    )
    private String password;

}
