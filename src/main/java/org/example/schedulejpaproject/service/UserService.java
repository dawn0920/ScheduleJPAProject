package org.example.schedulejpaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.SignUpResponseDto;
import org.example.schedulejpaproject.dto.UserResponseDto;
import org.example.schedulejpaproject.entity.User;
import org.example.schedulejpaproject.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor // 클래스 내 final 혹은 @NonNull 이 붙은 필드만 포함하는 생성자
public class UserService {
    private final UserRepository userRepository;

    // 생성(회원가입)
    public SignUpResponseDto signUp(String name, String email, String password) {
        User user = new User(name, email, password);
        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId(), saveUser.getName(), saveUser.getEmail());
    }

    // 조회
    public UserResponseDto findById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 찾을 수 없습니다.");
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getName(), findUser.getEmail());
    }

    // 수정
    @Transactional
    public void updatePassword(int id, String oldPassword, String newPassword) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!findUser.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findUser.updatePassword(newPassword);
    }

    public void delete(int id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }



}
