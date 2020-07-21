package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Product;
import com.siit.spring.domain.model.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductEntityToProductDtoMapper implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product source) {
        return ProductDto.builder()
                .currency(source.getCurrency())
                .image(source.getImage())
                .name(source.getName())
                .price(source.getPrice())
                .productId(source.getProductId())
                .quantity(source.getQuantity())
                .reserved(source.getReserved())
                .status(source.getStatus())
                .addedBy(source.getAddedBy())
                .dateAdded(source.getDateAdded())
                .dateModified(source.getDateModified())
                .build();
    }
}
