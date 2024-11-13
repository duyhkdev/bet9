package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.SanPhamDTO;
import com.duyhk.bet9.entity.LoaiSanPham;
import com.duyhk.bet9.entity.SanPham;
import com.duyhk.bet9.entity.SanPhamChiTiet;
import com.duyhk.bet9.repository.SanPhamChiTietRepository;
import com.duyhk.bet9.repository.SanPhamRepository;
import com.duyhk.bet9.service.SanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// findAll, findByID, create, update, delete
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class SanPhamServiceIplm implements SanPhamService {
    final SanPhamRepository sanPhamRepo;
    final SanPhamChiTietRepository sanPhamChiTietRepo;
    final ModelMapper mapper;

    @Override
    public List<SanPhamDTO> findAll(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 0 : pageIndex - 1;
        pageSize = pageSize == null ? 5 : pageSize;

        List<SanPham> listEntity = sanPhamRepo.findAll(
                PageRequest.of(pageIndex, pageSize)).getContent();
        List<SanPhamDTO> listDto = new ArrayList<>();

        for (SanPham entity : listEntity) {
            listDto.add(mapper.map(entity, SanPhamDTO.class));
        }
        return listDto;
    }

    @Override
    public List<SanPhamDTO> search(String ten, Long loaiSanPhamId) {
        List<SanPham> listEntity = sanPhamRepo.search(ten, loaiSanPhamId)
                .orElse(new ArrayList<>());
        List<SanPhamDTO> listDto  = new ArrayList<>();
        for (SanPham entity : listEntity) {
            listDto.add(mapper.map(entity, SanPhamDTO.class));
        }

        List<SanPhamDTO> listDtoList =
                listEntity.stream().map(x -> mapper.map(x, SanPhamDTO.class)).collect(Collectors.toList());


        return listDto;
    }

    @Override
    public SanPhamDTO findById(Long id) {
        SanPham entity = sanPhamRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Khong tim thay id sp")
        );
        return null;
    }

    @Override
    public String create(SanPhamDTO dto) throws IOException {
        List<String> anhSanPham = new ArrayList<>();
        if (dto.getFiles() == null) {
            throw new RuntimeException("Files is null");
        }
        for (MultipartFile file : dto.getFiles()) {
            String filename = file.getOriginalFilename();
            String path = "D:/workspace/std/images";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File fileUpload = new File(path + "/" + filename);
            file.transferTo(fileUpload);
            anhSanPham.add(filename);
        }
        SanPham entity = mapper.map(dto, SanPham.class);
        entity.setAnhSanPham(anhSanPham);
        entity.setSoLuongTonKho(0L);
        entity.setSoLuongDaBan(0L);
        entity.setGia(0L);
        entity.setTrangThai(0);
        sanPhamRepo.save(entity);
        return "Them thanh cong";
    }


    @Override
    public String update(Long id, SanPhamDTO dto) throws IOException {
        SanPham sanPham = sanPhamRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay id sp"));
        List<String> anhSanPham = new ArrayList<>();
        if (dto.getFiles() == null) {
            throw new RuntimeException("Files is null");
        }
        for (MultipartFile file : dto.getFiles()) {
            String filename = file.getOriginalFilename();
            String path = "D:/workspace/std/images";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File fileUpload = new File(path + "/" + filename);
            file.transferTo(fileUpload);
            anhSanPham.add(filename);
        }
        SanPham entity = mapper.map(dto, SanPham.class);
        entity.setAnhSanPham(anhSanPham);
        entity.setTrangThai(0);
        sanPhamRepo.save(entity);

        return "Sua thanh cong";
    }

    @Override
    public String delete(Long id) {
        List<SanPhamChiTiet> list = sanPhamChiTietRepo.findBySanPhamId(id)
                .orElse(new ArrayList<>());
        sanPhamChiTietRepo.deleteAll(list);
        sanPhamRepo.deleteById(id);
        return "Xoa thanh cong";
    }
}
