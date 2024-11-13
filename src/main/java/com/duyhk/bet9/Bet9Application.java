package com.duyhk.bet9;

import com.duyhk.bet9.entity.LoaiSanPham;
import com.duyhk.bet9.repository.LoaiSanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Bet9Application {
    public static void main(String[] args) {
        SpringApplication.run(Bet9Application.class, args);
    }

}
