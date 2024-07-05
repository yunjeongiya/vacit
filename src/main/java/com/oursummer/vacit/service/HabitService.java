package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.domain.Theme;
import com.oursummer.vacit.dto.habit.HabitCreateRequest;
import com.oursummer.vacit.repository.HabitRepository;
import com.oursummer.vacit.repository.StickerRepository;
import com.oursummer.vacit.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final ThemeRepository themeRepository;
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

}
