package com.duyhk.bet9.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ThongTinDatHangDTO {
    String soDienThoai;
    String diaChi;
    String hoVaTen;
    Long gioHangId; // tk => gh => ghct
    // dat it sp trong gh
//    List<Long> listGhctId;
}
