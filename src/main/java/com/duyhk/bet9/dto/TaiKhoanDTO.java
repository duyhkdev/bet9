package com.duyhk.bet9.dto;

import com.duyhk.bet9.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TaiKhoanDTO {
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
    Integer trangThai;
}
