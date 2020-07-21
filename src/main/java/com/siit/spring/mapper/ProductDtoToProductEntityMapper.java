package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Product;
import com.siit.spring.domain.model.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ProductDtoToProductEntityMapper implements Converter<ProductDto, Product> {
    @Override
    public Product convert(ProductDto source) {
       return Product.builder()
               .currency(source.getCurrency())
               .image(source.getImage())
               .name(source.getName())
               .price(source.getPrice())
               .productId(source.getProductId())
               .quantity(source.getQuantity())
               .reserved(source.getReserved())
               .status(source.getStatus())
               .addedBy(source.getAddedBy())
               .dateAdded(LocalDateTime.now())
               .dateModified(LocalDateTime.now())
               .build();
    }
}
