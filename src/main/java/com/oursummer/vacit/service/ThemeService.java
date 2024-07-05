package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.Theme;
import com.oursummer.vacit.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;

    public Theme getThemeById(Long themeId) {
        return themeRepository.findById(themeId).orElseThrow(() -> new IllegalArgumentException("해당 테마가 없습니다."));
    }
}
