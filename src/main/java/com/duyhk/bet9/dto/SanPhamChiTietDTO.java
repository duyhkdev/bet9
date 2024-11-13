package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTietDTO {
    Long id;
    String ma;
    String ten;
    Long gia;
    Long soLuongTonKho;
    Long soLuongDaBan;
    Integer trangThai;

    SanPhamDTO sanPham;
    KichCoDTO kichCo;
    MauSacDTO mauSac;
}
