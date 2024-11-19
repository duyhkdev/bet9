package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.HoaDonChiTietTaiQuayDTO;
import com.duyhk.bet9.entity.HoaDon;
import com.duyhk.bet9.entity.HoaDonChiTiet;
import com.duyhk.bet9.entity.SanPham;
import com.duyhk.bet9.entity.SanPhamChiTiet;
import com.duyhk.bet9.repository.HoaDonChiTietRepo;
import com.duyhk.bet9.repository.HoaDonRepository;
import com.duyhk.bet9.repository.SanPhamChiTietRepository;
import com.duyhk.bet9.repository.SanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BanHangTaiQuayService {

    /*
        1. tạo 1 hoa don => status dang cho
        2. them cac san pham vao hoa don => update lai so luong san pham
        them spct -> hoa don chi tiet
        3. cap nhat them xoa huy
        4. thanh toan
     */
    /*
        entity -> (dto) -> repository -> service -> controller

        dto, exception, cac thu vien ben ngoai (modelmapper, ...)

     */

    final HoaDonChiTietRepo hoaDonChiTietRepo;
    final HoaDonRepository hoaDonRepo;
    final SanPhamChiTietRepository sanPhamChiTietRepo;
    final SanPhamRepository sanPhamRepo;

    public String taoHoaDon(){
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(generateBillCode());
        hoaDon.setTongSoSanPham(0l);
        hoaDon.setTongSoTien(0l);
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setTrangThai(1); // Trang thai dang cho
        hoaDon.setLoaiHoaDon(2);
        hoaDonRepo.save(hoaDon);
        return "Tao hoa don thanh cong";
    }

    public String themVaoGioHang(HoaDonChiTietTaiQuayDTO dto){
        HoaDon hoaDon = hoaDonRepo.findById(dto.getHoaDonId())
                .orElseThrow(() -> new RuntimeException("Hoa don khong ton tai"));
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepo.findById(dto.getSpctId())
                .orElseThrow(() -> new RuntimeException("San pham chi tiet khong ton tai"));
        //kiểm tra xem trong gi�� hàng của 1 th��ng user có spct chưa

        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findByHoaDonIdAndSanPhamChiTietId(dto.getHoaDonId(),dto.getSpctId())
                .orElse(null);
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + dto.getSoLuong());
            hoaDonChiTiet.setThanhTien(sanPhamChiTiet.getGia() * dto.getSoLuong());
        }else{
            hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonChiTiet.setSoLuong(dto.getSoLuong());
            hoaDonChiTiet.setThanhTien(dto.getSoLuong() * sanPhamChiTiet.getGia());
            hoaDonChiTiet.setDonGia(sanPhamChiTiet.getGia());
            hoaDon.setTongSoSanPham(hoaDon.getTongSoSanPham() + 1);
        }
        //update so luong san pham chi tiet
        sanPhamChiTiet.setSoLuongTonKho(sanPhamChiTiet.getSoLuongTonKho() - dto.getSoLuong());

        SanPham sanPham = sanPhamChiTiet.getSanPham();
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - dto.getSoLuong());

        //update tong so san pham va tong so tien
        hoaDon.setTongSoTien(hoaDon.getTongSoTien() + (sanPhamChiTiet.getGia() * dto.getSoLuong()));

        hoaDonChiTietRepo.save(hoaDonChiTiet);
        hoaDonRepo.save(hoaDon);
        sanPhamChiTietRepo.save(sanPhamChiTiet);
        sanPhamRepo.save(sanPham);
        return "them vao gio hang thanh cong";
    }

    public String capNhatSoLuong(Long soLuongMoi, Long hdctId){

        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findById(hdctId)
                .orElseThrow(() -> new RuntimeException("Hoa don chi tiet khong ton tai"));
        HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
        SanPhamChiTiet sanPhamChiTiet = hoaDonChiTiet.getSanPhamChiTiet();
        SanPham sanPham = sanPhamChiTiet.getSanPham();
        // tong tien cu - thanh tien cua so luong cu + thanh tien cua so luong moi
        Long soLuongCu = hoaDonChiTiet.getSoLuong();
        Long thanhTienCu = hoaDonChiTiet.getThanhTien();
        Long thanhTienMoi = sanPhamChiTiet.getGia() * soLuongMoi;

        // soluong ton kho + soluongtronghd - soluongtronghdmoi
        // dang con 5 trong hoa don dang la 7
        // update hd thanh 8 => dang con 4
        // 5 + 7 - 8
        sanPhamChiTiet.setSoLuongTonKho(sanPhamChiTiet.getSoLuongTonKho() + soLuongCu - soLuongMoi);
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() + soLuongCu - soLuongMoi);
        hoaDon.setTongSoTien(hoaDon.getTongSoTien() - thanhTienCu + thanhTienMoi);
        hoaDonChiTiet.setSoLuong(soLuongMoi);
        hoaDonChiTiet.setDonGia(sanPhamChiTiet.getGia());
        hoaDonChiTiet.setThanhTien(thanhTienMoi);

        sanPhamRepo.save(sanPham);
        sanPhamChiTietRepo.save(sanPhamChiTiet);
        hoaDonRepo.save(hoaDon);
        hoaDonChiTietRepo.save(hoaDonChiTiet);

        return "Cap nhat thanh cong";
    }

    // hoan lai so luong cua hdct cho sp
    public String xoaSanPhamKhoiHoaDon(){

        return "Xoa San pham thanh cong";
    }

    // hoan lai toan bo sl cho cac sp
    public String huyHoaDon(Long hoaDonId){
        HoaDon hoaDon = hoaDonRepo.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Hoa don da bi huy"));
        if (hoaDon.getTrangThai() == 0) {
            throw new RuntimeException("Hoa don da bi huy");
        }
        List<HoaDonChiTiet> lhdct = hoaDonChiTietRepo.findByHoaDonId(hoaDonId).get();

        hoaDon.setTrangThai(0); // Trang thai dang cho
        hoaDonRepo.save(hoaDon);
        return "Huy hoa don thanh cong";
    }

    public String thanhToan(){
        return "";
    }

    //
    public String generateBillCode(){
        return "";
    }
}
