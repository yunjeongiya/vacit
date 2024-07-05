package com.oursummer.vacit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StickerController {

    @GetMapping("/stickers")
    public ResponseEntity<Object> sticker() {

        Map<String, String> resp = new HashMap<>();
        resp.put("name", "sticker");
        resp.put("description", "sticker description");

        return ResponseEntity.ok(
    }

    @GetMapping("/stickers/{stickerId}")
    public ResponseEntity<Object> stickerDetail(@PathVariable Long stickerId) {
        return ResponseEntity.ok("stickerDetail");
    }
}
