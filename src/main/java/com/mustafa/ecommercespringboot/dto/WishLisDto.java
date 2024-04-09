package com.mustafa.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishLisDto {
    private String uuid;
    private String userUuid;
    private String productUuid;
}
