package com.duyhk.bet9.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    Integer loaiHoaDon; // 1 online, 2 tại quầy

    @ManyToOne
    TaiKhoan taiKhoan;
}
