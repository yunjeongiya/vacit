package com.oursummer.vacit.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettingResponse {
    private String username;
    private String email;
    private String role;
    private float criterion;
    private String nickname;
    private String phone;
    private String photo;
    private String statusMsg;
}
