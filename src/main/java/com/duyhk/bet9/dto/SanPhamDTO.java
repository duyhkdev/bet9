package com.duyhk.bet9.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPhamDTO {
    Long id;
    String ma;
    @NotNull(message = "Ten khong duoc de trong")
    String ten;
    Long gia;
    Long soLuongTonKho;
    Long soLuongDaBan;
    String moTa;
    Integer trangThai;
    LoaiSanPhamDTO loaiSanPham;
    List<String> anhSanPham;

    @JsonIgnore
    List<MultipartFile> files;
}
