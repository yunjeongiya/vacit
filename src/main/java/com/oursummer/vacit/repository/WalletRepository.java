package com.oursummer.vacit.repository;

import com.oursummer.vacit.domain.DailyCheck;
import com.oursummer.vacit.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
