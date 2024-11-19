package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HoaDonChiTietTaiQuayDTO {
    /*
    so luong
    hoa don
    spct
     */
    Long soLuong;
    Long hoaDonId;
    Long spctId;
}
