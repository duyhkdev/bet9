package com.duyhk.bet9.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Table(name = "san_pham")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ma;
    String ten;
    Long gia;
    Long soLuongTonKho;
    Long soLuongDaBan;
    String moTa;
    Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "loai_san_pham_id")
    LoaiSanPham loaiSanPham;

    @CollectionTable(name = "anh_san_pham")
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> anhSanPham;

}
