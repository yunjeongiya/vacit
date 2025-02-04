package com.oursummer.vacit.dto.user;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.dto.habit.HabitDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitsResponse {
    List<HabitDetailResponse> habits;
}
