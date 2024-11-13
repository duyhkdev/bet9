package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HoaDonChiTietDTO {
    Long donGia;
    Long soLuong;
    Long thanhTien;
    SanPhamChiTietDTO sanPhamChiTiet;
    HoaDonDTO hoaDon;
}
