package org.example.schedulejpaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulejpaproject.dto.SignUpResponseDto;
import org.example.schedulejpaproject.entity.User;
import org.example.schedulejpaproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 클래스 내 final 혹은 @NonNull 이 붙은 필드만 포함하는 생성자
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String name, String email, String password) {
        User user = new User(name, email, password);
        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId(), saveUser.getName(), saveUser.getEmail());
    }
}
