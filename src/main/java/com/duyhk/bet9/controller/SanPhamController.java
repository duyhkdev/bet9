package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.ResponseDTO;
import com.duyhk.bet9.dto.SanPhamDTO;
import com.duyhk.bet9.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SanPhamController {
    final SanPhamService sanPhamService;

    @GetMapping
    public ResponseDTO<List<SanPhamDTO>> getAll(@RequestParam(required = false) Integer pageIndex,
                                                @RequestParam(required = false) Integer pageSize) {
        return ResponseDTO.<List<SanPhamDTO>>builder()
                .data(sanPhamService.findAll(pageIndex, pageSize))
                .message("Lay du lieu thanh cong")
                .build();
    }

    @GetMapping("/search")
    public ResponseDTO<List<SanPhamDTO>> search(@RequestParam String ten,
                                                @RequestParam Long loaiSanPhamId) {
        return ResponseDTO.<List<SanPhamDTO>>builder()
                .data(sanPhamService.search(ten, loaiSanPhamId))
                .message("Tim kiem san pham thanh cong")
                .build();
    }
    @GetMapping("/{id}")
    public ResponseDTO<SanPhamDTO> getById(@PathVariable Long id) {
        return ResponseDTO.<SanPhamDTO>builder()
               .data(sanPhamService.findById(id))
               .message("Lay san pham chi tiet thanh cong")
               .build();
    }

    @PostMapping
    public ResponseDTO<Void> create(@ModelAttribute @Valid SanPhamDTO dto) throws IOException {
        return ResponseDTO.<Void>builder()
                .message(sanPhamService.create(dto))
                .status(200)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@ModelAttribute SanPhamDTO dto, @PathVariable Long id) throws IOException {
        return ResponseDTO.<Void>builder()
                .message(sanPhamService.update(id, dto))
                .status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id) {
        return ResponseDTO.<Void>builder()
                .message(sanPhamService.delete(id))
                .build();
    }

}
