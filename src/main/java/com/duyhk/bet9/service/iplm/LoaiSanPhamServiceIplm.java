package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.LoaiSanPhamDTO;
import com.duyhk.bet9.entity.LoaiSanPham;
import com.duyhk.bet9.repository.LoaiSanPhamRepository;
import com.duyhk.bet9.service.LoaiSanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoaiSanPhamServiceIplm implements LoaiSanPhamService {

    final LoaiSanPhamRepository loaiSanPhamRepo;

    @Override
    public List<LoaiSanPhamDTO> findAll() {
        List<LoaiSanPham> listEntity =  loaiSanPhamRepo.findAll();
        List<LoaiSanPhamDTO> listDto = new ArrayList<>();
        for (LoaiSanPham entity : listEntity) {
            LoaiSanPhamDTO dto = LoaiSanPhamDTO.builder()
                   .id(entity.getId())
                   .ten(entity.getTen())
                   .build();
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public LoaiSanPham findById(Long id) {
        return loaiSanPhamRepo.findById(id)
                .orElse(null);
    }

    @Override
    public String create() {
        return null;
    }

    @Override
    public String update(Long id) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}
