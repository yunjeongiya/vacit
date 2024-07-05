package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.domain.Theme;
import com.oursummer.vacit.dto.APIResponse;
import com.oursummer.vacit.dto.habit.*;
import com.oursummer.vacit.dto.sticker.AllStickersResponse;
import com.oursummer.vacit.service.HabitService;
import com.oursummer.vacit.service.StickerService;
import com.oursummer.vacit.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HabitController {

    private final HabitService habitService;
    private final StickerService stickerService;
    private final ThemeService themeService;

    @PostMapping("/habits/add")
    public ResponseEntity<Object> addHabit(@RequestBody HabitCreateRequest habitCreateRequest) {
        Habit habit = habitService.createHabit(habitCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/habits/like")
    public ResponseEntity<Object> likeHabit(HabitLikeRequest habitLikeRequest) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/habits/memo")
    public ResponseEntity<Object> updateHabitMemo(HabitEditMemoRequest habitEditMemoRequest) {
        return ResponseEntity.ok().build();
    }
    @PutMapping("/habits/check")
    public ResponseEntity<Object> checkHabit(HabitCheckRequest habitCheckRequest) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/habits/{habitId}")
    public ResponseEntity<Object> getHabit(@PathVariable Long habitId) {
        Habit habit = habitService.getHabitById(habitId);
        Theme theme = themeService.getThemeById(habit.getThemeId());
        Sticker sticker = stickerService.getStickerById(theme.getStickerId());
        HabitDetailResponse habitDetailResponse = HabitDetailResponse.builder()
                .userId(habit.getUserId())
                .userNickname("사용자 닉네임")    //Todo : 사용자 계정 연동 후 수정
                .likes(habit.getLikeCnt())
                .habitId(habit.getId())
                .habitName(habit.getName())
                .progress(-1.0f)    //Todo : 습관 진행률 계산 후 수정
                .stickerImg(sticker.getImage())
                .backgroundColor(theme.getBackgroundColor())
                .startDate(habit.getStartDate())
                .endDate(habit.getEndDate())
                .checkedDates(List.of())    //Todo : 체크한 날짜 조회 후 수정
                .memo(habit.getMemo())
                .build();
        return ResponseEntity.ok().body(APIResponse.ofSuccess("습관 조회 성공", habitDetailResponse));

    }
}
