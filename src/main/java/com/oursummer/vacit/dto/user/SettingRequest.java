package com.oursummer.vacit.dto.user;

import lombok.Data;

@Data
public class SettingRequest {
    private Long userId;
    private String email;
    private String role;
    private float criterion;
    private String nickname;
    private String phone;
    private String photo;
    private String statusMsg;
}
