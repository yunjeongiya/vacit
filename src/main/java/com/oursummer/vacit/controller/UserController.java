package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.dto.APIResponse;
import com.oursummer.vacit.dto.user.HabitsResponse;
import com.oursummer.vacit.dto.user.StickerPurchaseRequest;
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
public class UserController {

    private final HabitService habitService;
    private final StickerService stickerService;
    @GetMapping("/users/{userId}/habbits/")
    public ResponseEntity<Object> getHabbits(@PathVariable Long userId) {
        log.info("get user habbits: {}", userId);
        List<Habit> habits = habitService.getHabitsByUserId(userId);
        HabitsResponse habitsResponse = new HabitsResponse(habits);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("사용자의 습관 조회 성공", habitsResponse));
    }

    @GetMapping("/users/{userId}/stickers")
    public ResponseEntity<Object> getStickers(@PathVariable Long userId) {
        log.info("get user stickers: {}", userId);
        List<Sticker> stickers = stickerService.getStickerByUserId(userId);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("보유중인 스티커 조회 성공", stickers));
    }
    @PostMapping("/users/purchase")
    public ResponseEntity<Object> purchaseSticker(@RequestBody StickerPurchaseRequest request) {
        log.info("purchase sticker: {}", request);
        stickerService.purchaseSticker(request.getUserId(), request.getStickerId());
        return ResponseEntity.ok().body(APIResponse.ofSuccess("구매 성공", null));
    }

}
