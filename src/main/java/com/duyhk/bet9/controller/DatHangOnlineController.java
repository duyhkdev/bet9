package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.ResponseDTO;
import com.duyhk.bet9.dto.ThongTinDatHangDTO;
import com.duyhk.bet9.service.iplm.DatHangOnlineServiceIplm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/online")
public class DatHangOnlineController {

    // khi dat hang can co thong tin gi (hoa don)
    // no xu ly gi (chuyen het sp trong gio hang vao hoa don <=> xoa het sp trong gh)
    final DatHangOnlineServiceIplm datHangOnlineService;

    @PostMapping("dat-hang")
    public ResponseDTO<String> datHang(@RequestBody ThongTinDatHangDTO request){
        return ResponseDTO.<String>builder()
               .message(datHangOnlineService.datHang(request))
               .data("maDonHang")
               .build();
    }
}
