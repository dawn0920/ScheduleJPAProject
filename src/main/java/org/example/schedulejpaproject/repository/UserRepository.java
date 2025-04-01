package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.dto.UserResponseDto;
import org.example.schedulejpaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    default User findByIdOrElseThrow(int id) {
        return findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다. = " + id));
    }

    Optional<User> findUserByName(String name);

    default User findUserByNameOrElseThrow(String name) {
        return findUserByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저 이름을 찾을 수 없습니다. = " + name));
    }

    // 이메일과 비밀번호로 유저 id 찾기
    public User findIdByEmailAndPassword(String email, String password);

    // findById를 사용하여 UserResponseDto로 변환
    default UserResponseDto findByIdAndConvertToDto(int id) {
        User user = findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다. = " + id));
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
}
