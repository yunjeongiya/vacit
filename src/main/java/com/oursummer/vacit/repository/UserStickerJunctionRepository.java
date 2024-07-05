package com.oursummer.vacit.repository;

import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.domain.UserStickerJunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStickerJunctionRepository extends JpaRepository<UserStickerJunction, Long> {

    List<UserStickerJunction> findAllByUserId(Long userId);

    UserStickerJunction findByUserIdAndStickerId(Long userId, Long stickerId);
}
