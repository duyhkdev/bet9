package com.duyhk.bet9.service;

import com.duyhk.bet9.dto.LoaiSanPhamDTO;
import com.duyhk.bet9.entity.LoaiSanPham;

import java.util.List;

public interface LoaiSanPhamService {
    List<LoaiSanPhamDTO> findAll();
    LoaiSanPham findById(Long id);
    String create();
    String update(Long id);
    String delete(Long id);
}
