package org.example.schedulejpaproject.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.*;
import org.example.schedulejpaproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public  ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request // 세션 사용을 위해 필요
    ) {
        // 로그인 유저 조회
        LoginResponseDto responseDto = userService.login(
                requestDto.getEmail(), requestDto.getPassword());

        // 로그인 성공시 세션에 사용자 ID 저장
        HttpSession session = request.getSession();
        session.setAttribute("user", responseDto.getId());

        // 응답 바디에 메시지 담기
        Map<String, String> responsBody = new HashMap<>();
        responsBody.put("message", "로그인 성공");
        responsBody.put("userId", String.valueOf(responseDto.getId()));

        return ResponseEntity.ok(responsBody);
    }

    @PostMapping("/logout") // 로그아웃
    public ResponseEntity<Map<String, String>> logout(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate(); // 세션 삭제
        }

        // 응답 바디에 메시지 담기
        Map<String, String> responsBody = new HashMap<>();
        responsBody.put("message", "로그아웃 성공");

        return ResponseEntity.ok(responsBody);
    }

}
