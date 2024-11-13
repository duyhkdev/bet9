package com.duyhk.bet9.service;

import com.duyhk.bet9.dto.SanPhamChiTietDTO;

import java.util.List;

public interface SanPhamChiTietService {
    // get post put delete

    List<SanPhamChiTietDTO> findAll();

    SanPhamChiTietDTO findById(Long id);
    String create(SanPhamChiTietDTO dto);
    String update(Long id, SanPhamChiTietDTO dto);
    String delete(Long id);
}
