package com.oursummer.vacit.repository;

import com.oursummer.vacit.domain.Setting;
import com.oursummer.vacit.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
}
