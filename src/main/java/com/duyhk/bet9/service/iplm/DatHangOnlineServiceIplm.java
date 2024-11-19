package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.constant.TrangThai;
import com.duyhk.bet9.dto.ThongTinDatHangDTO;
import com.duyhk.bet9.entity.*;
import com.duyhk.bet9.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
    hang khach hang
    Đặt online: them vao gio (khong tru so luong) => dat hang (tru so luong)
    Ban tai quy: them vao gio hang (tru so luong) => thanh toan (....)
 */
@RequiredArgsConstructor
@Service
public class DatHangOnlineServiceIplm {
    final HoaDonRepository hoaDonRepo;
    final HoaDonChiTietRepo hoaDonChiTietRepo;
    final GioHangRepo gioHangRepo;
    final GioHangChiTietRepo gioHangChiTietRepo;
    final TaiKhoanRepo taiKhoanRepo;
    final SanPhamRepository sanPhamRepo;
    final SanPhamChiTietRepository sanPhamChiTietRepo;
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


        List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();
        List<HoaDonChiTiet> hdcts = new ArrayList<>();
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setDonGia(gioHangChiTiet.getSanPhamChiTiet().getGia());
            hdct.setSoLuong(gioHangChiTiet.getSoLuong());
            hdct.setThanhTien(gioHangChiTiet.getSoLuong() * gioHangChiTiet.getSanPhamChiTiet().getGia());
            hdct.setSanPhamChiTiet(gioHangChiTiet.getSanPhamChiTiet());

            SanPhamChiTiet sanPhamChiTiet = gioHangChiTiet.getSanPhamChiTiet();
            SanPham sanPham = sanPhamChiTiet.getSanPham();

            sanPhamChiTiet.setSoLuongTonKho(sanPhamChiTiet.getSoLuongTonKho() - gioHangChiTiet.getSoLuong());
            sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - gioHangChiTiet.getSoLuong());

            sanPhamChiTietList.add(sanPhamChiTiet);
            hdct.setHoaDon(hoaDon);
            hdcts.add(hdct);
            sanPhamRepo.save(sanPham);
        }
        gioHang.setTongSoSanPham(0l);
        sanPhamChiTietRepo.saveAll(sanPhamChiTietList);
        hoaDonChiTietRepo.saveAll(hdcts);
        gioHangChiTietRepo.deleteAll(gioHangChiTietList);
        gioHangRepo.save(gioHang);
        return "Dat hang thanh cong";
    }

    public String capNhapTrangThai(Integer trangThaiMoi, Long hoaDonId){
        HoaDon hoaDon = hoaDonRepo.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Hoa don khong ton tai"));
        if(trangThaiMoi == TrangThai.DA_HUY.status &&
                (hoaDon.getTrangThai() != TrangThai.CHO_LAY_HANG.status
                && hoaDon.getTrangThai() != TrangThai.DANG_CHO.status)){
            throw new RuntimeException("Hoa don khong the huy");
        };
        // validate ...

        /*
            Nê ttm = huy  => update lại so luong sp
            hoàn thành => update
         */
        List<HoaDonChiTiet> listHdct = hoaDonChiTietRepo.findByHoaDonId(hoaDonId)
                .orElse(null);
        if(trangThaiMoi == TrangThai.DA_HUY.status){
            huyHoaDon(listHdct);
        }else if(trangThaiMoi == TrangThai.HOAN_THANH.status){
            hoanThanhHoaDon(listHdct);
        }
        hoaDon.setTrangThai(trangThaiMoi);
        hoaDonRepo.save(hoaDon);
        return "Cap nhat trang thai thanh cong";
    }
    private void hoanThanhHoaDon(List<HoaDonChiTiet> listHdct) {
        // cap nhat lai so luong
        for(HoaDonChiTiet hdct : listHdct){
            SanPhamChiTiet spct = hdct.getSanPhamChiTiet();
            SanPham sp = spct.getSanPham();

            //logic xu ly
            spct.setSoLuongDaBan(spct.getSoLuongDaBan() + hdct.getSoLuong());
            sp.setSoLuongDaBan(sp.getSoLuongDaBan() + hdct.getSoLuong());

            //luu vao db
            sanPhamChiTietRepo.save(spct);
            sanPhamRepo.save(sp);
        }
        // lây hoa don tu hoa don chi tiet
        HoaDon hoaDon = listHdct.get(0).getHoaDon();

        TaiKhoan taiKhoan = hoaDon.getTaiKhoan();

        taiKhoan.setTongHoaDon(taiKhoan.getTongHoaDon() + 1);
        taiKhoan.setTongTien(taiKhoan.getTongTien() + hoaDon.getTongSoTien());

        boolean checkType = (taiKhoan.getTongHoaDon() >= 70 && taiKhoan.getTongTien() >= 15000000);
        taiKhoan.setHangTaiKhoan(checkType ? 2 : 1);
        taiKhoanRepo.save(taiKhoan);

    }
    private void huyHoaDon(List<HoaDonChiTiet> listHdct) {
        // cap nhat lai so luong
        for(HoaDonChiTiet hdct : listHdct){
            SanPhamChiTiet spct = hdct.getSanPhamChiTiet();
            SanPham sp = spct.getSanPham();

            //logic xu ly
            spct.setSoLuongTonKho(spct.getSoLuongTonKho() + hdct.getSoLuong());
            sp.setSoLuongTonKho(sp.getSoLuongTonKho() + hdct.getSoLuong());

            //luu vao db
            sanPhamChiTietRepo.save(spct);
            sanPhamRepo.save(sp);
        }
    }

}
