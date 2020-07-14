package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private String name;

    private BigDecimal price;

    private Long quantity;

    private Long reserved;

    private String image;

    private String currency;

    private Integer status;

    private Integer addedBy;

    private CartProductsDto cartProduct;
}
