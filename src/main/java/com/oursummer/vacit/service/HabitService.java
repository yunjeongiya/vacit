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
        Habit habit = Habit.builder()
                .name(habitCreateRequest.getName())
                .startDate(habitCreateRequest.getStartDate())
                .endDate(habitCreateRequest.getEndDate())
                .memo(habitCreateRequest.getMemo())
                .build();
        Theme theme = Theme.builder()
                .stickerId(habitCreateRequest.getStickerId())
                .backgroundColor(habitCreateRequest.getBackgroundColor())
                .build();
        habitRepository.save(habit);
        themeRepository.save(theme);
        return habit;
    }

}
