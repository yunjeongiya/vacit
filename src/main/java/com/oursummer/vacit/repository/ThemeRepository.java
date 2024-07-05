package com.oursummer.vacit.repository;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
