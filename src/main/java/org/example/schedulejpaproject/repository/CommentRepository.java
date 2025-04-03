package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Collection<Comment> findByScheduleId(int scheduleId);
}
