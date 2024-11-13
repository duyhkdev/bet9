package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.LoaiSanPhamDTO;
import com.duyhk.bet9.entity.LoaiSanPham;
import com.duyhk.bet9.service.LoaiSanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 168.2.2.2:8080/api/loai-san-pham
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/loai-san-pham")
// This suppression is necessary because Spring Boot will automatically generate the necessary methods for us.
public class LoaiSanPhamController {
    // controller -> service -> repository
    final LoaiSanPhamService loaiSanPhamService;

    // get, post, put, delete

    @GetMapping
    public List<LoaiSanPhamDTO> getAll() {
        return loaiSanPhamService.findAll();
    }
}
