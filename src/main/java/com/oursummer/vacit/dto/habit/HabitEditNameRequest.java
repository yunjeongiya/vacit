package com.oursummer.vacit.dto.habit;

import lombok.Data;

@Data
public class HabitEditNameRequest {
    private Long habitId;
    private String name;
}
