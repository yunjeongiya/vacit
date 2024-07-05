package com.oursummer.vacit.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Email
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    @JoinColumn(name = "wallet_id")
    private Long walletId;
}
