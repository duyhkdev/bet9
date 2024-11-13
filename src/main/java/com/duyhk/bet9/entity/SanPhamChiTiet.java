package com.duyhk.bet9.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTiet {
    //  ma varchar(255), ten varchar(255), gia bigint,
    // so_luong_ton_kho bigint, so_luong_da_ban bigint, trang_thai int,
    // san_pham_id bigint (khóa phụ),
    // mau_sac_id bigint (khóa phụ), kich_co_id bigint (khóa phụ)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ma;
    String ten;
    Long gia;
    Long soLuongTonKho;
    Long soLuongDaBan;
    Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    SanPham sanPham;
    @ManyToOne
    @JoinColumn(name = "kich_co_id")
    KichCo kichCo;
    @ManyToOne
    @JoinColumn(name = "mau_sac_id")
    MauSac mauSac;
}
