package com.oursummer.vacit.dto.habit;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class HabitDetailResponse {
    private Long userId;
    private String userNickname;
    private Integer likes;
    private Long habitId;
    private String habitName;
    private Float progress;
    private String stickerImg;
    private String backgroundColor;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<LocalDate> checkedDates;
    private String memo;
}
