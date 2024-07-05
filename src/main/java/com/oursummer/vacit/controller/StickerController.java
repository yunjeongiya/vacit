package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.dto.APIResponse;
import com.oursummer.vacit.dto.sticker.AllStickersResponse;
import com.oursummer.vacit.service.StickerService;
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
public class StickerController {

    private final StickerService stickerService;

    @GetMapping("/stickers")
    public ResponseEntity<Object> sticker() {
        log.info("모든 스티커 조회");
        List<Sticker> stickers = stickerService.getSticker();
        AllStickersResponse stickersResponse = new AllStickersResponse(stickers);
        return ResponseEntity.ok().body(APIResponse.ofSuccess("모든 스티커 조회", stickersResponse));
    }

    @GetMapping("/stickers/{stickerId}")
    public ResponseEntity<Object> stickerDetail(@PathVariable Long stickerId) {
        log.info("스티커 상세 정보 조회: {}", stickerId);
        Sticker sticker = stickerService.getStickerById(stickerId);
        return ResponseEntity.ok().body(sticker);
    }
}
