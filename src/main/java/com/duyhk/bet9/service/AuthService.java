package com.duyhk.bet9.service;

import com.duyhk.bet9.dto.DangKyDTO;
import com.duyhk.bet9.dto.DangNhapDTO;

public interface AuthService {
    String dangKy(DangKyDTO dto);
    String dangNhap(DangNhapDTO dto);
}
