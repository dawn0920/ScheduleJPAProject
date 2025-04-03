package org.example.schedulejpaproject.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleRequestDto {

    @Size(max = 20, message = "제목은 20글자 내로 작성해주세요. ")
    private String title;

    private String contents;

    @Size(max = 10, message = "이름은 10글자 내로 작성해주세요. ")
    private String name;

}
