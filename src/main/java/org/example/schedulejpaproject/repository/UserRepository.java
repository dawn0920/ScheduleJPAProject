package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Member;
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

}
