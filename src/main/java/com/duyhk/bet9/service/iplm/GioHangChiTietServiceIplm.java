package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.GioHangChiTietDTO;
import com.duyhk.bet9.entity.GioHang;
import com.duyhk.bet9.entity.GioHangChiTiet;
import com.duyhk.bet9.entity.SanPhamChiTiet;
import com.duyhk.bet9.repository.GioHangChiTietRepo;
import com.duyhk.bet9.repository.GioHangRepo;
import com.duyhk.bet9.repository.SanPhamChiTietRepository;
import com.duyhk.bet9.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GioHangChiTietServiceIplm implements GioHangChiTietService {
    private final GioHangChiTietRepo gioHangChiTietRepo;
    private final GioHangRepo gioHangRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    @Override
    public String themVaoGioHang(GioHangChiTietDTO dto) {

        GioHang gioHang = gioHangRepo.findById(dto.getGioHangId())
                .orElseThrow(() -> new RuntimeException("Tai khoan khong duoc ho tro mua hang"));
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepo.findById(dto.getSanPhamChiTietDTO().getId())
                .orElseThrow(() -> new RuntimeException("San pham khong ton tai"));
        //kiểm tra xem trong giỏ hàng của 1 thằng user có spct chưa
        /*
            => nếu có rồi => update la số lượng trong giỏ hanng
            => nếu chưa có => thêm mới
         */
        GioHangChiTiet isExist =
                gioHangChiTietRepo.findBySanPhamChiTietIdAndGioHangId(sanPhamChiTiet.getId(), dto.getGioHangId())
                .orElse(null);
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        if (isExist == null){
            gioHangChiTiet.setSoLuong(dto.getSoLuong());
            gioHang.setTongSoSanPham(gioHang.getTongSoSanPham() + 1);
        } else{
            gioHangChiTiet.setId(isExist.getId());
            gioHangChiTiet.setSoLuong(isExist.getSoLuong() + dto.getSoLuong());
        }
        gioHangChiTiet.setGioHang(gioHang);
        gioHangChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        gioHangChiTietRepo.save(gioHangChiTiet);
        gioHangRepo.save(gioHang);
        return "Them thanh cong";
    }
    @Override
    public String suaSoLuong(Long gioHangChiTietId, Long soLuongMoi) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(gioHangChiTietId)
                .orElseThrow(() -> new RuntimeException("Gio hang chi tiet khong ton tai"));
        gioHangChiTiet.setSoLuong(soLuongMoi);
        gioHangChiTietRepo.save(gioHangChiTiet);
        return "Sua thanh cong";
    }
    @Override
    public String xoaKhoiGioHang(Long gioHangChiTietId) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(gioHangChiTietId)
                .orElseThrow(() -> new RuntimeException("Gio hang chi tiet da bi xoa"));
        GioHang gioHang = gioHangChiTiet.getGioHang();
        gioHang.setTongSoSanPham(gioHang.getTongSoSanPham() - 1);
        gioHangRepo.save(gioHang);
        gioHangChiTietRepo.deleteById(gioHangChiTietId);
        return "Xoa thanh cong";
    }


}
