package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.GioHangChiTietDTO;
import com.duyhk.bet9.dto.ResponseDTO;
import com.duyhk.bet9.service.GioHangChiTietService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/gio-hang-chi-tiet")
public class GioHangChiTietController {
    private final GioHangChiTietService gioHangChiTietService;

    @PostMapping("/them-moi")
    public ResponseDTO<Void> themMoi(@RequestBody GioHangChiTietDTO dto) {
        return ResponseDTO.<Void>builder()
                .message(gioHangChiTietService.themVaoGioHang(dto))
                .status(200)
                .build();
    }

    @PostMapping("/sua-so-luong/{id}/{soLuong}")
    public ResponseDTO<Void> sua(@PathVariable Long id,
                                 @PathVariable Long soLuong) {
        return ResponseDTO.<Void>builder()
                .message(gioHangChiTietService.suaSoLuong(id, soLuong))
                .status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> xoa(@PathVariable Long id) {
        return ResponseDTO.<Void>builder()
                .message(gioHangChiTietService.xoaKhoiGioHang(id))
                .status(200)
                .build();
    }
}
