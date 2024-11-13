package com.duyhk.bet9.service;

import com.duyhk.bet9.dto.GioHangChiTietDTO;

public interface GioHangChiTietService {
    String themVaoGioHang(GioHangChiTietDTO dto);
    String suaSoLuong(Long gioHangChiTietId, Long soLuongMoi);
    String xoaKhoiGioHang(Long gioHangChiTietId);
}
