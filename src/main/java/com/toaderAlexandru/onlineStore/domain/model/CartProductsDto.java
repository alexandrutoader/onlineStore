package com.toaderAlexandru.onlineStore.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartProductsDto {
    private Long cartId;

    private Long productId;

    private Long quantity;

    private Integer status;
}
