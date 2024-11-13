package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.ResponseDTO;
import com.duyhk.bet9.dto.SanPhamChiTietDTO;
import com.duyhk.bet9.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/san-pham-chi-tiet")
public class SanPhamChiTietController {
    private final SanPhamChiTietService sanPhamChiTietService;

    @GetMapping
    public ResponseDTO<List<SanPhamChiTietDTO>> findAll() {
        return ResponseDTO.<List<SanPhamChiTietDTO>>builder()
                .data(sanPhamChiTietService.findAll())
                .message("Lay du lieu san pham chi tiet thanh cong")
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<SanPhamChiTietDTO> findById(@PathVariable Long id) {
        return ResponseDTO.<SanPhamChiTietDTO>builder()
                .data(sanPhamChiTietService.findById(id))
                .message("Lay san pham chi tiet theo id thanh cong")
                .build();
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody SanPhamChiTietDTO dto) {
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(sanPhamChiTietService.create(dto))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable Long id,
                                    @RequestBody SanPhamChiTietDTO dto) {
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(sanPhamChiTietService.update(id, dto)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id) {
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(sanPhamChiTietService.delete(id)).build();
    }
}
