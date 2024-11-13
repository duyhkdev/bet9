package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HoaDonDTO {
    Long id;
    String maHoaDon;
    Long tongSoSanPham;
    Long tongSoTien;
    String soDienThoai;
    String diaChi;
    String hoVaTen;
    String maNhanVien;
    String ngayTao;
    String ngayHoanThanh;
    String lyDoHuy;
    Integer trangThai; // 1 đang cho, 2 cho lay hang, 3 dang giao hang, 4 da hoan thanh, 0 la huy
    Integer loaiHoaDon;
    TaiKhoanDTO taiKhoan;
}
