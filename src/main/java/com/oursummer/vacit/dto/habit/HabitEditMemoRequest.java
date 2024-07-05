package com.oursummer.vacit.dto.habit;

import lombok.Data;

@Data
public class HabitEditMemoRequest {
    private Long habitId;
    private String memo;
}
