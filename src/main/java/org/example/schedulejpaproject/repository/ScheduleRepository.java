package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    default Schedule findByIdOrElseThrow(int id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다. : " + id));
    }
}
