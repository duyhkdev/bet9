package com.duyhk.bet9.repository;

import com.duyhk.bet9.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    Optional<List<SanPhamChiTiet>> findBySanPhamId(Long sanPhamId);
}
