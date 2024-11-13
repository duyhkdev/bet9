package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.DangKyDTO;
import com.duyhk.bet9.dto.DangNhapDTO;
import com.duyhk.bet9.dto.ResponseDTO;
import com.duyhk.bet9.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {
    final AuthService authService;
    @PostMapping("/dang-ky")
    public ResponseDTO<Void> dangKy(@RequestBody DangKyDTO dto) {
        // Implement login logic here
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(authService.dangKy(dto))
                .build();
    }

    @PostMapping("/dang-nhap")
    public ResponseDTO<Void> dangNhap(@RequestBody DangNhapDTO dto) {
        // Implement login logic here
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(authService.dangNhap(dto))
                .build();
    }
}
