package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
