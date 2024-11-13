package com.duyhk.bet9.repository;

import com.duyhk.bet9.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietRepo extends JpaRepository<GioHangChiTiet, Long> {
    Optional<GioHangChiTiet> findBySanPhamChiTietIdAndGioHangId(Long id, Long gioHangId);

    Optional<List<GioHangChiTiet>> findByGioHangId(Long gioHangId);
}
