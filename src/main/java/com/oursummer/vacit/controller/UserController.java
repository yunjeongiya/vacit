package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.dto.APIResponse;
import com.oursummer.vacit.dto.user.*;
import com.oursummer.vacit.service.HabitService;
import com.oursummer.vacit.service.StickerService;
import com.oursummer.vacit.service.UserService;
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
    private final UserService userService;
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
    @GetMapping("/users/{userId}/wallet")
    public ResponseEntity<Object> getWallet(@PathVariable Long userId) {
        log.info("get user wallet: {}", userId);
        UserWalletResponse wallet = userService.getWallet(userId);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("보유중인 돈 조회 성공", wallet));
    }
    @GetMapping("/users/{userId}/setting")
    public ResponseEntity<Object> getSetting(@PathVariable Long userId) {
        log.info("get user setting: {}", userId);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("설정 정보 조회 성공", userService.getSetting(userId)));
    }
    @PatchMapping("/users/setting")
    public ResponseEntity<Object> updateSetting(@RequestBody SettingRequest request) {
        log.info("update user setting: {}", request);
        userService.updateSetting(request);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("설정 정보 수정 성공", null));
    }
    @GetMapping("/users/{userId}/statistics")
    public ResponseEntity<Object> getStatistics(@PathVariable Long userId) {
        log.info("get user statistics: {}", userId);
        StatisticsResponse statistics = habitService.getStatistics(userId);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("통계 조회 성공", statistics));
    }
}
