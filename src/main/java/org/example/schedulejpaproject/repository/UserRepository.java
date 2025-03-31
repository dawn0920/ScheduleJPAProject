package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;

public interface UserRepository extends JpaRepository<User, Integer> {
}
