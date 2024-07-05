package com.oursummer.vacit.dto.habit;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HabitCheckListRequest {
    private Long habitId;
    private List<LocalDate> dates;
    private Boolean isCheck;
}
