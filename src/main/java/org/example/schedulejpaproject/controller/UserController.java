package org.example.schedulejpaproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.SignUpRequestDto;
import org.example.schedulejpaproject.dto.SignUpResponseDto;
import org.example.schedulejpaproject.dto.UserResponseDto;
import org.example.schedulejpaproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup") // 생성 (회원가입)
    public ResponseEntity<SignUpResponseDto> signUp(
            @RequestBody SignUpRequestDto requestDto
            ) {
        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        requestDto.getName(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // 조회
    public ResponseEntity<UserResponseDto> findById(
            @PathVariable int id
    ) {
        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
