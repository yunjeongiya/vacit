package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.DailyCheck;
import com.oursummer.vacit.repository.DailyCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyCheckService {
    
    final DailyCheckRepository dailyCheckRepository;

    public void dailyCheck(Long habitId, LocalDate date, Boolean isCheck) {
        Optional<DailyCheck> dailyCheck = dailyCheckRepository.findByHabitIdAndDate(habitId, date);
        if (isCheck) {
            if (dailyCheck.isEmpty()) createDailyCheck(habitId, date);
        } else {
            dailyCheck.ifPresent(this::deleteDailyCheck);
        }
    }
    // 조회
    public List<DailyCheck> getDailyCheckList(Long habitId) {
        return dailyCheckRepository.findAllByHabitId(habitId);
    }
    // 생성
    public void createDailyCheck(Long habitId, LocalDate date) {
        dailyCheckRepository.save(DailyCheck.builder()
                .habitId(habitId)
                .date(date)
                .build());
    }
    // 삭제
    public void deleteDailyCheck(DailyCheck dailyCheck) {
        dailyCheckRepository.delete(dailyCheck);
    }
}
