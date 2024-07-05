package com.oursummer.vacit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JoinColumn(name = "habit_id")
    private Long habitId;

    @Column(nullable = false)
    private LocalDate date;
}
