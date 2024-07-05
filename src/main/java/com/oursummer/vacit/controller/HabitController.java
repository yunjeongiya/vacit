package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.dto.APIResponse;
import com.oursummer.vacit.dto.habit.HabitCheckRequest;
import com.oursummer.vacit.dto.habit.HabitCreateRequest;
import com.oursummer.vacit.dto.habit.HabitEditMemoRequest;
import com.oursummer.vacit.dto.habit.HabitLikeRequest;
import com.oursummer.vacit.dto.sticker.AllStickersResponse;
import com.oursummer.vacit.service.HabitService;
import com.oursummer.vacit.service.StickerService;
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
        return ResponseEntity.ok().build();
    }
}
