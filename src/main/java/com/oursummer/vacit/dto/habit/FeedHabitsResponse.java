package com.oursummer.vacit.dto.habit;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedHabitsResponse {
    public List<HabitDetailResponse> feeds;
}
