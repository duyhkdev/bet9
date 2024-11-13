package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.DangKyDTO;
import com.duyhk.bet9.dto.DangNhapDTO;
import com.duyhk.bet9.entity.GioHang;
import com.duyhk.bet9.entity.Role;
import com.duyhk.bet9.entity.TaiKhoan;
import com.duyhk.bet9.repository.GioHangRepo;
import com.duyhk.bet9.repository.TaiKhoanRepo;
import com.duyhk.bet9.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceIplm implements AuthService {
    final TaiKhoanRepo taiKhoanRepo;
    final GioHangRepo gioHangRepo;


    @Override
    public String dangKy(DangKyDTO dto) {
        // biểu thức chính quy
        // đệ quy
        String validatePhoneNumber = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
        if (!dto.getSoDienThoai().matches(validatePhoneNumber)){
            throw new RuntimeException("Số điện thoại không đúng đ��nh dạng");
        }
        TaiKhoan taiKhoan = TaiKhoan.builder()
                .soDienThoai(dto.getSoDienThoai())
                .matKhau(dto.getMatKhau())
                .trangThai(0)
                .role(Role.KHACHHANG)
                .tongTien(0l)
                .tongHoaDon(0l)
                .build();
        // create account
        taiKhoan = taiKhoanRepo.save(taiKhoan);

        GioHang gioHang = new GioHang();
        gioHang.setTaiKhoan(taiKhoan);
        gioHang.setTongSoSanPham(0l);
        // create cart
        gioHangRepo.save(gioHang);

        return "Đăng ký thành công";
    }

    @Override
    public String dangNhap(DangNhapDTO dto) {
        TaiKhoan taiKhoan = taiKhoanRepo.findBySoDienThoaiAndMatKhau(dto.getSoDienThoai(),dto.getMatKhau())
                        .orElse(null);
        if (taiKhoan == null)
            throw new RuntimeException("Tai khoan hoac mat khau khong chinh xac");

        return "Đăng nhập thành công";
    }
}
