package com.oursummer.vacit.dto.habit;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HabitCheckRequest {
    private Long habitId;
    private LocalDate date;
    private Boolean bool;
}
