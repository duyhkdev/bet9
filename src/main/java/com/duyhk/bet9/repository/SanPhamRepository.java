package com.duyhk.bet9.repository;

import com.duyhk.bet9.dto.SanPhamDTO;
import com.duyhk.bet9.entity.LoaiSanPham;
import com.duyhk.bet9.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Query("""
        select s from SanPham s 
        where (:ten is null or :ten  = "" or s.ten like concat('%',:ten,'%') ) 
        and (:loaiSanPhamId is null or s.loaiSanPham.id = :loaiSanPhamId)
    """)
    Optional <List<SanPham>> search(@Param("ten") String ten,
                                   @Param("loaiSanPhamId") Long loaiSanPhamId);
}
// ten like '%ao' => tìm kiem ten ket thuc bang ao
// ten like 'ao%' => tìm kiem ten bat dau bang ao
// ten like '%ao%' => tìm kiem ten chua tu  ao