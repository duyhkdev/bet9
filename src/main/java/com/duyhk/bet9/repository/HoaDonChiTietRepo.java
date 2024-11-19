package com.duyhk.bet9.repository;

import com.duyhk.bet9.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet, Long> {
    Optional<List<HoaDonChiTiet>> findByHoaDonId(Long id);

    Optional<HoaDonChiTiet> findByHoaDonIdAndSanPhamChiTietId(Long hoaDonId, Long spctId);
}
