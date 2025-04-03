package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.entity.Comment;
import org.example.schedulejpaproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Collection<Comment> findByScheduleId(int scheduleId);

    default Comment findByIdOrElseThrow(int commentId) {
        return findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다. : " + commentId));
    }

}
