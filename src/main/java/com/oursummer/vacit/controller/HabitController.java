package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.dto.APIResponse;
import com.oursummer.vacit.dto.habit.*;
import com.oursummer.vacit.service.DailyCheckService;
import com.oursummer.vacit.service.HabitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HabitController {

    private final HabitService habitService;
    private final DailyCheckService dailyCheckService;

    @PostMapping("/habits/add")
    public ResponseEntity<Object> addHabit(@RequestBody HabitCreateRequest habitCreateRequest) {
        Habit habit = habitService.createHabit(habitCreateRequest);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("습관 생성 성공", habit.getId()));
    }

    @PatchMapping("/habits/like")
    public ResponseEntity<Object> likeHabit(HabitLikeRequest habitLikeRequest) {
        habitService.likeHabit(habitLikeRequest.getHabitId());
        return ResponseEntity.ok().body(APIResponse.ofSuccess("좋아요 성공", null));
    }

    @PatchMapping("/habits/memo")
    public ResponseEntity<Object> updateHabitMemo(HabitEditMemoRequest habitEditMemoRequest) {
        habitService.updateHabitMemo(habitEditMemoRequest.getHabitId(), habitEditMemoRequest.getMemo());
        return ResponseEntity.ok().body(APIResponse.ofSuccess("메모 수정 성공", null));
    }
    @PatchMapping("/habits/name")
    public ResponseEntity<Object> updateHabitName(HabitEditNameRequest habitEditNameRequest) {
        habitService.updateHabitName(habitEditNameRequest.getHabitId(), habitEditNameRequest.getName());
        return ResponseEntity.ok().body(APIResponse.ofSuccess("이름 수정 성공", null));
    }
    @PutMapping("/habits/check")
    public ResponseEntity<Object> checkHabit(HabitCheckRequest habitCheckRequest) {
        dailyCheckService.dailyCheck(habitCheckRequest.getHabitId(), habitCheckRequest.getDate(), habitCheckRequest.getIsCheck());
        return ResponseEntity.ok().body(APIResponse.ofSuccess("체크 성공", null));
    }
    @PatchMapping("/habits/check")
    public ResponseEntity<Object> checkHabitList(HabitCheckListRequest habitCheckListRequest) {
        for (LocalDate checkDate : habitCheckListRequest.getDates()) {
            dailyCheckService.dailyCheck(habitCheckListRequest.getHabitId(), checkDate, habitCheckListRequest.getIsCheck());
        }
        return ResponseEntity.ok().body(APIResponse.ofSuccess("체크 성공", null));
    }
    @GetMapping("/habits/{habitId}")
    public ResponseEntity<Object> getHabit(@PathVariable Long habitId) {
        HabitDetailResponse habitDetailResponse = habitService.getHabitById(habitId);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("습관 조회 성공", habitDetailResponse));
    }
    @GetMapping("/feed/habits")
    public ResponseEntity<Object> getFeedHabits() {
        FeedHabitsResponse feedHabitsResponse = habitService.getFeedHabits();
        return ResponseEntity.ok().body(APIResponse.ofSuccess("피드 습관 조회 성공", feedHabitsResponse));
    }
}
