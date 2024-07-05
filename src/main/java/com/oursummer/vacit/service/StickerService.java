package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.repository.StickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StickerService {

    private final StickerRepository stickerRepository;

    public List<Sticker> getSticker() {
        return stickerRepository.findAll();
    }

    public Sticker getStickerById(Long stickerId) {
        return stickerRepository.findById(stickerId).orElseThrow(() -> new IllegalArgumentException("해당 스티커가 없습니다."));
    }
}
