package com.oursummer.vacit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private float criterion;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false)
    private String statusMsg;
}
