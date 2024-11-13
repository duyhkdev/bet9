package com.duyhk.bet9.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TaiKhoan {
    /*
    tai_khoan: id bigint, ma varchar(10), email varchar(255), mat_khau varchar(20), ho_va_ten varchar(100),
    role: smallin, tong_hoa_don bigint, tong_tien bigint, hang_tai_khoan int, trang_thai int

     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ma;
    String email;
    String soDienThoai;
    String matKhau;
    String hoVaTen;
    Role role;
    Long tongHoaDon;
    Long tongTien;
    Integer hangTaiKhoan; // 1 là thư��ng, 2 là vip => tongHoaDon >= 10 v tongTien >= 20000000
    Integer trangThai; // 0 L�� ngư�ng hoạt đ�ng, 1 L�� hoạt đ�ng
}
