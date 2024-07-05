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



}
