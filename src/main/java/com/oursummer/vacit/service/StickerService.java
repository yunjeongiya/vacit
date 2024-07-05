package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.domain.UserStickerJunction;
import com.oursummer.vacit.repository.StickerRepository;
import com.oursummer.vacit.repository.UserRepository;
import com.oursummer.vacit.repository.UserStickerJunctionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class StickerService {

    private final StickerRepository stickerRepository;
    private final UserRepository userRepository;
    private final UserStickerJunctionRepository userStickerJunctionRepository;
    public List<Sticker> getSticker() {
        return stickerRepository.findAll();
    }

    public Sticker getStickerById(Long stickerId) {
        return stickerRepository.findById(stickerId).orElseThrow(() -> new IllegalArgumentException("해당 스티커가 없습니다."));
    }
    // 사용자가 보유중인 스티커 조회
    public List<Sticker> getStickerByUserId(Long userId) {
        List<UserStickerJunction> userStickerJunction = userStickerJunctionRepository.findAllByUserId(userId);
        return userStickerJunction.stream().map(UserStickerJunction::getSticker).toList();
    }
    // 스티커 구매
    public void purchaseSticker(Long userId, Long stickerId) {
        UserStickerJunction userStickerJunction = userStickerJunctionRepository.findByUserIdAndStickerId(userId, stickerId);
        if (userStickerJunction != null) {
            throw new IllegalArgumentException("이미 구매한 스티커입니다.");
        }
        UserStickerJunction newUserStickerJunction = UserStickerJunction.builder()
                .sticker(stickerRepository.findById(stickerId).orElseThrow(() -> new IllegalArgumentException("해당 스티커가 없습니다.")))
                .user(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.")))
                .build();
        userStickerJunctionRepository.save(newUserStickerJunction);
    }
}
