package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.ThongTinDatHangDTO;
import com.duyhk.bet9.entity.*;
import com.duyhk.bet9.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DatHangOnlineServiceIplm {
    final HoaDonRepository hoaDonRepo;
    final HoaDonChiTietRepo hoaDonChiTietRepo;
    final GioHangRepo gioHangRepo;
    final GioHangChiTietRepo gioHangChiTietRepo;
    final TaiKhoanRepo taiKhoanRepo;
    // xem ctiet don hang
    // lay danh sach don hang
    // update trang thai don hang
    public String datHang(ThongTinDatHangDTO request) {
        GioHang gioHang = gioHangRepo.findById(request.getGioHangId())
                .orElseThrow(() -> new RuntimeException("tai khoan da bi xoa"));
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietRepo.findByGioHangId(request.getGioHangId())
                .orElseThrow(() -> new RuntimeException("Vui long them sp vao gio hang"));

        HoaDon hoaDon = new HoaDon();
        hoaDon.setSoDienThoai(request.getSoDienThoai());
        hoaDon.setDiaChi(request.getDiaChi());
        hoaDon.setHoVaTen(request.getHoVaTen());
        hoaDon.setTaiKhoan(gioHang.getTaiKhoan());
        hoaDon = hoaDonRepo.save(hoaDon);

        List<HoaDonChiTiet> hdcts = new ArrayList<>();
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setDonGia(gioHangChiTiet.getSanPhamChiTiet().getGia());
            hdct.setSoLuong(gioHangChiTiet.getSoLuong());
            hdct.setThanhTien(gioHangChiTiet.getSoLuong() * gioHangChiTiet.getSanPhamChiTiet().getGia());
            hdct.setSanPhamChiTiet(gioHangChiTiet.getSanPhamChiTiet());
            hdct.setHoaDon(hoaDon);
            hdcts.add(hdct);
        }
        gioHang.setTongSoSanPham(0l);
        hoaDonChiTietRepo.saveAll(hdcts);
        gioHangChiTietRepo.deleteAll(gioHangChiTietList);
        gioHangRepo.save(gioHang);
        return "Dat hang thanh cong";
    }
}
