package com.oursummer.vacit.repository;

import com.oursummer.vacit.domain.DailyCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyCheckRepository extends JpaRepository<DailyCheck, Long> {
    Optional<DailyCheck> findByHabitIdAndDate(Long habitId, LocalDate date);

    List<DailyCheck> findAllByHabitId(Long habitId);
}
