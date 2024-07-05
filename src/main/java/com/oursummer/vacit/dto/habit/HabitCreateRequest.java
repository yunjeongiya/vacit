package com.oursummer.vacit.dto.habit;

import lombok.Data;

import java.awt.*;
import java.time.LocalDate;

@Data
public class HabitCreateRequest {
    private Long userId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String backgroundColor;
    private Long stickerId;
    private String memo;
}
