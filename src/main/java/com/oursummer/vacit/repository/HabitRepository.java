package com.oursummer.vacit.repository;

import com.oursummer.vacit.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}
