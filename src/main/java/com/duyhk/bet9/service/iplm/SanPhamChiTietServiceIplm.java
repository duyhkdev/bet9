package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.SanPhamChiTietDTO;
import com.duyhk.bet9.entity.MauSac;
import com.duyhk.bet9.entity.SanPham;
import com.duyhk.bet9.entity.SanPhamChiTiet;
import com.duyhk.bet9.repository.MauSacRepository;
import com.duyhk.bet9.repository.SanPhamChiTietRepository;
import com.duyhk.bet9.repository.SanPhamRepository;
import com.duyhk.bet9.service.SanPhamChiTietService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamChiTietServiceIplm implements SanPhamChiTietService {

    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    private final MauSacRepository mauSacRepo;
    private final ModelMapper mapper;
    private final SanPhamRepository sanPhamRepo;

    @Override
    public List<SanPhamChiTietDTO> findAll() {
        List<SanPhamChiTiet> list = sanPhamChiTietRepo.findAll();
        List<SanPhamChiTietDTO> listDtos = new ArrayList<>();
        for (SanPhamChiTiet x : list) {
            listDtos.add(mapper.map(x, SanPhamChiTietDTO.class));
        }
        return listDtos;
    }

    @Override
    public SanPhamChiTietDTO findById(Long id) {
        SanPhamChiTiet entity = sanPhamChiTietRepo.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, SanPhamChiTietDTO.class);
        } else return null;
    }

    @Override
    public String create(SanPhamChiTietDTO dto) {
        MauSac msTest = mauSacRepo.findById(dto.getMauSac().getId()).orElse(null);
        if(msTest == null){
            //
        }
        SanPhamChiTiet sanPhamChiTiet = mapper.map(dto, SanPhamChiTiet.class);
        sanPhamChiTiet = sanPhamChiTietRepo.save(sanPhamChiTiet);

        SanPham sanPham = sanPhamChiTiet.getSanPham();
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() + sanPhamChiTiet.getSoLuongTonKho());
        sanPhamRepo.save(sanPham);
        //
        return "Them thanh cong";
    }

    @Override
    public String update(Long id, SanPhamChiTietDTO dto) {
        SanPhamChiTiet entity = sanPhamChiTietRepo.findById(id).orElseThrow(
                () -> new RuntimeException("khong tim thay sp chi tiet")
        );
        Long soLuongTonKhoCu = entity.getSoLuongTonKho();
        SanPhamChiTiet sanPhamChiTiet = mapper.map(dto, SanPhamChiTiet.class);
        sanPhamChiTiet = sanPhamChiTietRepo.save(sanPhamChiTiet);

        SanPham sanPham = sanPhamChiTiet.getSanPham();
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - soLuongTonKhoCu + sanPhamChiTiet.getSoLuongTonKho());
        sanPhamRepo.save(sanPham);
        return "update thanh cong";
    }

    @Override
    public String delete(Long id) {
        sanPhamChiTietRepo.deleteById(id);
        return "Xoa thanh cong";
    }
}
// tao ra 1 sp áo polo in hinh con ngua // 0
// tạo ra các ctsp  (áo polo in hình con ngựa size M màu  đen)