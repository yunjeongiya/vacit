package com.oursummer.vacit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StickersResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String status;
    private int price;
    private String createdAt;
    private String updatedAt;
}
