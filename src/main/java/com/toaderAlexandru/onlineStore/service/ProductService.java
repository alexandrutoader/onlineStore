package com.toaderAlexandru.onlineStore.service;

import com.toaderAlexandru.onlineStore.domain.entity.Product;
import com.toaderAlexandru.onlineStore.domain.model.ProductDto;
import com.toaderAlexandru.onlineStore.exception.OrderNotFoundException;
import com.toaderAlexandru.onlineStore.mapper.ProductDtoToProductEntityMapper;
import com.toaderAlexandru.onlineStore.mapper.ProductEntityToProductDtoMapper;
import com.toaderAlexandru.onlineStore.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoToProductEntityMapper productDtoToProductEntityMapper;
    private final ProductEntityToProductDtoMapper productEntityToProductDtoMapper;

    public ProductDto create(ProductDto productDto) {
        Product product = productDtoToProductEntityMapper.convert(productDto);

        if (null == product) {
            throw new NullPointerException("Product cannot be null!");
        }

        product = productRepository.save(product);
        return productEntityToProductDtoMapper.convert(product);
    }

    public ProductDto findById(long productId) {
        return productRepository.findById(productId)
                .map(productEntityToProductDtoMapper::convert)
                .orElseThrow(() -> new OrderNotFoundException("The product with provided id cannot be found!"));
    }

    public List<ProductDto> getAll() {
        return productRepository.getAll()
                .stream()
                .map(productEntityToProductDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new OrderNotFoundException("Product with provided id cannot be found!"));

        updateFields(product, productDto);
    }

    private void updateFields(Product product, ProductDto productDto) {
        if (null != productDto.getCurrency()) {
            product.setCurrency(productDto.getCurrency());
        }

        if (null != productDto.getImage()) {
            product.setImage(productDto.getImage());
        }

        if (null != productDto.getName()) {
            product.setName(productDto.getName());
        }

        if (null != productDto.getAddedBy()) {
            product.setAddedBy(productDto.getAddedBy());
        }

        if (null != productDto.getPrice()) {
            product.setPrice(productDto.getPrice());
        }

        if (null != productDto.getQuantity()) {
            product.setQuantity(productDto.getQuantity());
        }

        if (null != productDto.getReserved()) {
            product.setReserved(productDto.getReserved());
        }

        if (null != productDto.getStatus()) {
            product.setStatus(productDto.getStatus());
        }

        product.setDateModified(LocalDateTime.now());
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("The product with provided id cannot be found!"));

        productRepository.delete(product);
    }
}
