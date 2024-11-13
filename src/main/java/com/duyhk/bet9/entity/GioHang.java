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
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long tongSoSanPham;

    @OneToOne
    TaiKhoan taiKhoan;
}
