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
    public int successHabits;
    public int failHabits;
    public int activeHabits;
    public float successRatio;
}
