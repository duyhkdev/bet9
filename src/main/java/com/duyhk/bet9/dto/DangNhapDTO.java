package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DangNhapDTO {
    String soDienThoai; // username
    String matKhau;  // password
}
