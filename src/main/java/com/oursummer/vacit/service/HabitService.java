package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.DailyCheck;
import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.domain.Theme;
import com.oursummer.vacit.dto.habit.HabitCreateRequest;
import com.oursummer.vacit.dto.habit.HabitDetailResponse;
import com.oursummer.vacit.repository.HabitRepository;
import com.oursummer.vacit.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final StickerService stickerService;
    private final HabitRepository habitRepository;
    private final ThemeRepository themeRepository;
    private final DailyCheckService dailyCheckService;
    //습관 생성
    public Habit createHabit(HabitCreateRequest habitCreateRequest) {
        // 테마 생성
        Theme theme = Theme.builder()
                .stickerId(habitCreateRequest.getStickerId())
                .backgroundColor(habitCreateRequest.getBackgroundColor())
                .build();
        Theme SaveTheme = themeRepository.save(theme);
        // 습관 생성
        Habit habit = Habit.builder()
                .name(habitCreateRequest.getName())
                .startDate(habitCreateRequest.getStartDate())
                .endDate(habitCreateRequest.getEndDate())
                .memo(habitCreateRequest.getMemo())
                .themeId(SaveTheme.getId())
                .userId(-1L)    //Todo : 사용자 계정 연동 후 수정
                .build();
        return habitRepository.save(habit);
    }

    public HabitDetailResponse getHabitById(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
        Theme theme = themeRepository.findById(habit.getThemeId()).orElseThrow(() -> new IllegalArgumentException("해당 테마가 없습니다."));
        Sticker sticker = stickerService.getStickerById(theme.getStickerId());
        List<LocalDate> dailyCheckList = dailyCheckService.getDailyCheckList(habitId).stream().map(DailyCheck::getDate).toList();
        // 진행율 계산
        int totalDays = habit.getEndDate().compareTo(habit.getStartDate());
        int checkedDays = dailyCheckList.size();
        float progress = (float) checkedDays / totalDays;

        return HabitDetailResponse.builder()
                .userId(habit.getUserId())
                .userNickname("사용자 닉네임")    //Todo : 사용자 계정 연동 후 수정
                .likes(habit.getLikeCnt())
                .habitId(habit.getId())
                .habitName(habit.getName())
                .progress(progress)
                .stickerImg(sticker.getImage())
                .backgroundColor(theme.getBackgroundColor())
                .startDate(habit.getStartDate())
                .endDate(habit.getEndDate())
                .checkedDates(dailyCheckList)
                .memo(habit.getMemo())
                .build();
    }

    // 좋아요 추가
    public void likeHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
        habit.setLikeCnt(habit.getLikeCnt() + 1);
        habitRepository.save(habit);
    }
    // 메모 수정
    public void updateHabitMemo(Long habitId, String memo) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
        habit.setMemo(memo);
        habitRepository.save(habit);
    }
}
