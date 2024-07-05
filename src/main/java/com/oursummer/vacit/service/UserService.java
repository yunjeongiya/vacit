package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.User;
import com.oursummer.vacit.domain.Wallet;
import com.oursummer.vacit.dto.user.UserWalletResponse;
import com.oursummer.vacit.repository.UserRepository;
import com.oursummer.vacit.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public final WalletRepository walletRepository;

    // 보유중인 돈 조회
    public UserWalletResponse getWallet(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        Wallet wallet = walletRepository.findById(user.getWalletId()).orElseThrow(() -> new IllegalArgumentException("지갑 정보가 없습니다."));
        return new UserWalletResponse(wallet.getExp(), wallet.getCoin());
    }
}
