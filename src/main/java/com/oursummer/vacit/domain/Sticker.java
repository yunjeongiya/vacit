package com.oursummer.vacit.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int level;

    // create_at
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    // update_at
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        // Json 형태로 출력
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"description\":\"" + description + '\"' +
                ", \"image\":\"" + image + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"price\":" + price +
                ", \"level\":" + level +
                ", \"createdAt\":\"" + createdAt + '\"' +
                ", \"updatedAt\":\"" + updatedAt + '\"' +
                '}';
    }
}
