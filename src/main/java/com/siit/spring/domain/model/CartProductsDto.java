package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartProductsDto {
    private CartDto cart;

    private ProductDto product;

    private Long quantity;

    private Integer status;
}
