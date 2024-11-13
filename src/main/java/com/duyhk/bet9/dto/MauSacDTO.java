package com.duyhk.bet9.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MauSacDTO {
    Long id;
    @NotNull(message = "Ten khong duoc de trong")
    String ten;
}
