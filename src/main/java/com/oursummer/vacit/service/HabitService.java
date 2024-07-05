package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Theme;
import com.oursummer.vacit.dto.habit.HabitCreateRequest;
import com.oursummer.vacit.repository.HabitRepository;
import com.oursummer.vacit.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitService {

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

    public Habit getHabitById(Long habitId) {
        return habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
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
