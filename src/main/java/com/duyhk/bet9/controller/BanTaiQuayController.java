package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.HoaDonChiTietTaiQuayDTO;
import com.duyhk.bet9.dto.ResponseDTO;
import com.duyhk.bet9.service.iplm.BanHangTaiQuayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/bant-tai-quay")
public class BanTaiQuayController {

    final BanHangTaiQuayService banHangTaiQuayService;

    @PostMapping
    public ResponseDTO<Void> taoHoaDon() {
        // Implement logic to create a bill
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(banHangTaiQuayService.taoHoaDon())
                .build();
    }

    @PostMapping("/them-sp")
    public ResponseDTO<Void> themSanPham(@RequestBody HoaDonChiTietTaiQuayDTO dto) {
        // Implement logic to add product to bill
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(banHangTaiQuayService.themVaoGioHang(dto))
                .build();
    }

    @PutMapping("/sua-so-luon/{hdctId}/{soLuongMoi}")
    public ResponseDTO<Void> suaSoLuong(@PathVariable Long hdctId,
                                        @PathVariable Long soLuongMoi) {
        // Implement logic to update product quantity in bill
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(banHangTaiQuayService.capNhatSoLuong(soLuongMoi, hdctId))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> huyHoadon(@PathVariable Long id) {
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(banHangTaiQuayService.huyHoaDon(id))
                .build();
    }
}
