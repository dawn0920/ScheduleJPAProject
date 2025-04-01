package org.example.schedulejpaproject.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.*;
import org.example.schedulejpaproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
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

    @PatchMapping("/{id}") // 수정
    public ResponseEntity<Void> updatePassword(
        @PathVariable int id,
        @RequestBody UpdatePasswordRequestDto requestDto
    ) {
        userService.updatePassword(id, requestDto.getOldPassword(),
                requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // 삭제
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletResponse response // 쿠키값 세팅에 필요
    ) {
        // 로그인 유저 조회
        LoginResponseDto responseDto = userService.login(
                requestDto.getEmail(), requestDto.getPassword());

        if (responseDto == null) {
            return new ResponseEntity<>("이메일 또는 비밀번호가 비어있습니다.", HttpStatus.UNAUTHORIZED);
        }

        // 로그인 성공시 쿠키에 저장
        Cookie cookie = new Cookie("userId", String.valueOf(responseDto.getId()));
        response.addCookie(cookie);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/logout") // 로그아웃
    public ResponseEntity<Void> logout(
            HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

}
