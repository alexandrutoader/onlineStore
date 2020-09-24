package com.toaderAlexandru.onlineStore.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {
    private Long productId;

    private String name;

    private BigDecimal price;

    private Long quantity;

    private Long reserved;

    private String image;

    private String currency;

    private Integer status;

    private Integer addedBy;

    private LocalDateTime dateAdded;

    private LocalDateTime dateModified;
}
