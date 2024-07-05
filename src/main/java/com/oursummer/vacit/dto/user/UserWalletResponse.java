package com.oursummer.vacit.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWalletResponse {
    private int exp;
    private int coin;
}
