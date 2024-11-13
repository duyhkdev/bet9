package com.duyhk.bet9.repository;

import com.duyhk.bet9.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepo extends JpaRepository<TaiKhoan, Long> {
    Optional<TaiKhoan> findBySoDienThoaiAndMatKhau(String soDienThoai, String matKhau);
//    boolean existsBySoDienThoai(String soDienThoai);
}
