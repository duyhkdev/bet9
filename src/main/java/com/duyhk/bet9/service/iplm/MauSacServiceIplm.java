package com.duyhk.bet9.service.iplm;

import com.duyhk.bet9.dto.MauSacDTO;
import com.duyhk.bet9.entity.MauSac;
import com.duyhk.bet9.repository.MauSacRepository;
import com.duyhk.bet9.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MauSacServiceIplm implements MauSacService {
    private final MauSacRepository mauSacRepo;
    private final ModelMapper mapper;

    @Override
    public List<MauSacDTO> findAll(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 0 : pageIndex - 1 ;
        pageSize = pageSize == null ? 5 : pageSize;

        List<MauSacDTO> listDto = new ArrayList<>();
        List<MauSac> listEntity = mauSacRepo.findAll(PageRequest.of(pageIndex, pageSize)).getContent();
        for (MauSac x : listEntity) {
            MauSacDTO dto = new MauSacDTO();
            dto.setId(x.getId());
            dto.setTen(x.getTen());
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public MauSacDTO findById(Long id) {
        MauSac entity = mauSacRepo.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, MauSacDTO.class);
        }
        return null;
    }

    @Override
    public String create(MauSacDTO dto) {
        MauSac entity = mapper.map(dto, MauSac.class);
        mauSacRepo.save(entity);
        return "Tao thanh cong";
    }
    // hàm save sẽ update khi có id se tao moi khi khong co id
    @Override
    public String update(Long id, MauSacDTO dto) {
        MauSac entity = mauSacRepo.findById(id).orElse(null);
        if (entity!= null) {
            entity.setTen(dto.getTen());
            mauSacRepo.save(entity);
        }
        return "Sửa thanh cong";
    }

    @Override
    public String delete(Long id) {
        mauSacRepo.deleteById(id);
        return "Xoa thanh cong";
    }
}
