package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.dto.user.HabitsResponse;
import com.oursummer.vacit.service.HabitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final HabitService habitService;
    @GetMapping("/users/{userId}/habbits/")
    public ResponseEntity<Object> getHabbits(@PathVariable Long userId) {
        log.info("get user habbits: {}", userId);
        List<Habit> habits = habitService.getHabitsByUserId(userId);
        HabitsResponse habitsResponse = new HabitsResponse(habits);
        return ResponseEntity.ok().body(habitsResponse);
    }
}
