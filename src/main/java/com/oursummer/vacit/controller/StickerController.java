package com.oursummer.vacit.controller;

import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.service.StickerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StickerController {

    private final StickerService stickerService;

    @GetMapping("/stickers")
    public ResponseEntity<Object> sticker() {
        log.info("모든 스티커 조회");
        Map<String, String> resp = new HashMap<>();
        List<Sticker> stickers = stickerService.getSticker();
        resp.put("message", "모든 스티커 조회");
        resp.put("data", stickers.toString());
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/stickers/{stickerId}")
    public ResponseEntity<Object> stickerDetail(@PathVariable Long stickerId) {
        return ResponseEntity.ok("stickerDetail");
    }
}
