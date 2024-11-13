package com.duyhk.bet9.service;


import com.duyhk.bet9.dto.SanPhamChiTietDTO;
import com.duyhk.bet9.dto.SanPhamDTO;

import java.io.IOException;
import java.util.List;
//entity -> repository -> service -> controller

public interface SanPhamService {
    List<SanPhamDTO> findAll(Integer pageIndex, Integer pageSize);
    List<SanPhamDTO> search(String ten, Long loaiSanPhamId);

    SanPhamDTO findById(Long id);
    String create(SanPhamDTO dto) throws IOException;
    String update(Long id, SanPhamDTO dto) throws IOException;
    String delete(Long id);
}
