package com.oursummer.vacit.dto.sticker;

import com.oursummer.vacit.domain.Sticker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StickersResponse {
    public String message;
    public List<Sticker> data;
}
