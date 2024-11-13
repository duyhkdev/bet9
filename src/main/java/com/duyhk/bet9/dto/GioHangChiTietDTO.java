package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GioHangChiTietDTO {
    Long id;
    Long soLuong;
    SanPhamChiTietDTO sanPhamChiTietDTO;
    Long gioHangId;
}
