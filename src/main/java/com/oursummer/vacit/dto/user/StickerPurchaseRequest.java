package com.oursummer.vacit.dto.user;

import lombok.Data;

@Data
public class StickerPurchaseRequest {
    private Long userId;
    private Long stickerId;
}
