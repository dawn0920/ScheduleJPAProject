package org.example.schedulejpaproject.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateRequestDto {

    @Size(max = 10, message = "제목은 10글자 내로 작성해주세요. ")
    private String title;

    private String contents;

}
