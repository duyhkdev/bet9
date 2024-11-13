package com.duyhk.bet9.service;

import com.duyhk.bet9.dto.MauSacDTO;

import java.util.List;

public interface MauSacService {
    List<MauSacDTO> findAll(Integer pageIndex, Integer pageSize);
    MauSacDTO findById(Long id);
    String create(MauSacDTO dto);
    String update(Long id, MauSacDTO dto);
    String delete(Long id);
}
