package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.Setting;
import com.oursummer.vacit.domain.User;
import com.oursummer.vacit.domain.Wallet;
import com.oursummer.vacit.dto.user.SettingResponse;
import com.oursummer.vacit.dto.user.UserWalletResponse;
import com.oursummer.vacit.repository.SettingRepository;
import com.oursummer.vacit.repository.UserRepository;
import com.oursummer.vacit.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final WalletRepository walletRepository;
    private final SettingRepository settingRepository;
    // 보유중인 돈 조회
    public UserWalletResponse getWallet(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        Wallet wallet = walletRepository.findById(user.getWalletId()).orElseThrow(() -> new IllegalArgumentException("지갑 정보가 없습니다."));
        return new UserWalletResponse(wallet.getExp(), wallet.getCoin());
    }
    // 설정 정보 조회
    public SettingResponse getSetting(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        Setting setting = settingRepository.findById(user.getSettingId()).orElseThrow(() -> new IllegalArgumentException("설정 정보가 없습니다."));
        return SettingResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .criterion(setting.getCriterion())
                .nickname(setting.getNickname())
                .phone(setting.getPhone())
                .photo(setting.getPhoto())
                .statusMsg(setting.getStatusMsg())
                .build();
    }
}
