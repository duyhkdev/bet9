package com.duyhk.bet9.controller;

import com.duyhk.bet9.dto.MauSacDTO;
import com.duyhk.bet9.dto.ResponseDTO;
import com.duyhk.bet9.service.MauSacService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/mau-sac")
public class MauSacController {

    final MauSacService mauSacService;
    // post, put, get, delete
    @GetMapping
    public ResponseDTO<List<MauSacDTO>> getAll(
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize) {
        return ResponseDTO.<List<MauSacDTO>>builder()
                .data(mauSacService.findAll(pageIndex, pageSize))
                .message("Lay danh sach thanh cong")
                .status(200)
                .build();
    }
/*
    entity  ->  repository  >  service ----> controller
                                        DTO
    requestParam => api/mau-sac?{thamSo}={giatrithamso}&.....
    PathVariable => api/mau-sac/2
 */
    @GetMapping("/{id}")
    public ResponseDTO<MauSacDTO> getById(@PathVariable Long id) {
        return ResponseDTO.<MauSacDTO>builder()
                .data(mauSacService.findById(id))
                .message("Lay thanh cong")
                .status(200)
                .build();
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid MauSacDTO dto){
        return ResponseDTO.<Void>builder()
               .status(200)
               .message(mauSacService.create(dto))
               .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable Long id,
                                    @RequestBody MauSacDTO dto){
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(mauSacService.update(id, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        return ResponseDTO.<Void>builder()
                .status(200)
                .message(mauSacService.delete(id))
                .build();
    }
}
