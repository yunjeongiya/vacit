package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.DailyCheck;
import com.oursummer.vacit.domain.Habit;
import com.oursummer.vacit.domain.Sticker;
import com.oursummer.vacit.domain.Theme;
import com.oursummer.vacit.dto.habit.FeedHabitsResponse;
import com.oursummer.vacit.dto.habit.HabitCreateRequest;
import com.oursummer.vacit.dto.habit.HabitDetailResponse;
import com.oursummer.vacit.dto.user.StatisticsResponse;
import com.oursummer.vacit.repository.HabitRepository;
import com.oursummer.vacit.repository.ThemeRepository;
import com.oursummer.vacit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final StickerService stickerService;
    private final HabitRepository habitRepository;
    private final ThemeRepository themeRepository;
    private final DailyCheckService dailyCheckService;
    private final UserRepository userRepository;
    //습관 생성
    public Habit createHabit(HabitCreateRequest habitCreateRequest) {
        // 스티커 확인
        if (stickerService.getStickerById(habitCreateRequest.getStickerId()) == null) {
            throw new IllegalArgumentException("해당 스티커가 없습니다.");
        }
        // 구매한 스티커
        if (stickerService.getStickerByUserId(habitCreateRequest.getUserId()).stream().noneMatch(sticker -> sticker.getId().equals(habitCreateRequest.getStickerId()))) {
            throw new IllegalArgumentException("보유중인 스티커가 아닙니다.");
        }
        // 테마 생성
        Theme theme = Theme.builder()
                .stickerId(habitCreateRequest.getStickerId())
                .backgroundColor(habitCreateRequest.getBackgroundColor())
                .build();
        Theme SaveTheme = themeRepository.save(theme);
        // 습관 생성
        Habit habit = Habit.builder()
                .name(habitCreateRequest.getName())
                .startDate(habitCreateRequest.getStartDate())
                .endDate(habitCreateRequest.getEndDate())
                .memo(habitCreateRequest.getMemo())
                .themeId(SaveTheme.getId())
                .userId(habitCreateRequest.getUserId())
                .build();
        return habitRepository.save(habit);
    }

    public HabitDetailResponse getHabitById(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
        Theme theme = themeRepository.findById(habit.getThemeId()).orElseThrow(() -> new IllegalArgumentException("해당 테마가 없습니다."));
        Sticker sticker = stickerService.getStickerById(theme.getStickerId());
        List<LocalDate> dailyCheckList = dailyCheckService.getDailyCheckList(habitId).stream().map(DailyCheck::getDate).toList();
        // 진행율 계산
        int totalDays = habit.getEndDate().compareTo(habit.getStartDate());
        int checkedDays = dailyCheckList.size();
        float progress = (float) checkedDays / totalDays;
        String userNickname = userRepository.findById(habit.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.")).getUsername();
        return HabitDetailResponse.builder()
                .userId(habit.getUserId())
                .userNickname(userNickname)
                .likes(habit.getLikeCnt())
                .habitId(habit.getId())
                .habitName(habit.getName())
                .progress(progress)
                .stickerImg(sticker.getImage())
                .backgroundColor(theme.getBackgroundColor())
                .startDate(habit.getStartDate())
                .endDate(habit.getEndDate())
                .checkedDates(dailyCheckList)
                .memo(habit.getMemo())
                .build();
    }

    // 좋아요 추가
    public void likeHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
        habit.setLikeCnt(habit.getLikeCnt() + 1);
        habitRepository.save(habit);
    }
    // 메모 수정
    public void updateHabitMemo(Long habitId, String memo) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
        habit.setMemo(memo);
        habitRepository.save(habit);
    }
    public void updateHabitName(Long habitId, String name) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new IllegalArgumentException("해당 습관이 없습니다."));
        habit.setName(name);
        habitRepository.save(habit);
    }

    public List<Habit> getHabitsByUserId(Long userId) {
        return habitRepository.findByUserId(userId);
    }
    // 통계
    public StatisticsResponse getStatistics(Long userId) {
        List<Habit> habits = habitRepository.findByUserId(userId);
        int successHabitCount = (int) habits.stream().filter(habit -> habit.getStatus().equals("SUCCESS")).count();
        int failHabitCount = (int) habits.stream().filter(habit -> habit.getStatus().equals("FAIL")).count();
        List<Habit> activeHabits = habits.stream().filter(habit -> habit.getStatus().equals("ACTIVE")).toList();
        int activeHabitCount = activeHabits.size();
        // (스탬프수 / 현재일 - 시작일 ) == 습관 평균
        int totalStamp = activeHabits.stream().mapToInt(habit -> dailyCheckService.getDailyCheckList(habit.getId()).size()).sum();
        int totalDays = activeHabits.stream().mapToInt(habit -> habit.getStartDate().until(LocalDate.now()).getDays()).sum();
        int averageStamp = totalDays == 0 ? 0 : totalStamp / totalDays;

        return StatisticsResponse.builder()
                .successHabitCount(successHabitCount)
                .failHabitCount(failHabitCount)
                .activeHabitCount(activeHabitCount)
                .successRatio(averageStamp)
                .build();
    }

    public FeedHabitsResponse getFeedHabits() {
        List<Habit> habits = habitRepository.findAll();
        List<HabitDetailResponse> habitDetailResponses = habits.stream().map(habit -> getHabitById(habit.getId())).toList();
        return FeedHabitsResponse.builder()
                .feeds(habitDetailResponses)
                .build();
    }
}
