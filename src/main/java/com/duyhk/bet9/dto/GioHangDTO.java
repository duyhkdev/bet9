package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GioHangDTO {
    Long id;
    Long tongSoSanPham;
    TaiKhoanDTO taiKhoan;
}
