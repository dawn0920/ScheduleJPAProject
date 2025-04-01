package org.example.schedulejpaproject.repository;

import org.example.schedulejpaproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
