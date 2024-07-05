package com.oursummer.vacit.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
    public int successHabitCount;
    public int failHabitCount;
    public int activeHabitCount;
    public float successRatio;
}
