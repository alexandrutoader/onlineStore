package com.siit.spring.service;

import com.siit.spring.domain.entity.Cart;
import com.siit.spring.domain.model.CartDto;
import com.siit.spring.exception.CartNotFoundException;
import com.siit.spring.mapper.CartDtoToCartEntityMapper;
import com.siit.spring.mapper.CartEntityToCartDtoMapper;
import com.siit.spring.repository.CartRepository;
import com.siit.spring.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartDtoToCartEntityMapper cartDtoToCartEntityMapper;
    private final CartEntityToCartDtoMapper cartEntityToCartDtoMapper;

    public CartDto create(CartDto cartDto) {
        Cart cart = cartDtoToCartEntityMapper.convert(cartDto);

        if (null == cart) {
            throw new NullPointerException("Cart cannot be null!");
        }

        cartRepository.save(cart);
        return cartEntityToCartDtoMapper.convert(cart);
    }

    public CartDto findById(long cartId) {
        return cartRepository.findById(cartId)
                .map(cartEntityToCartDtoMapper::convert)
                .orElseThrow(() -> new CartNotFoundException("The cart with provided id cannot be found!"));
    }

    public List<CartDto> getAll() {
        return cartRepository.getAll()
                .stream()
                .map(cartEntityToCartDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(CartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getId())
                .orElseThrow(() -> new CartNotFoundException("Cart with provided id cannot be found!"));

        updateFields(cart, cartDto);
    }

    private void updateFields(Cart cart, CartDto cartDto) {
        if (null != cartDto.getStatus()) {
            cart.setStatus(cartDto.getStatus());
        }

        if (null != cartDto.getCustomerId()) {
            customerRepository.findById(cartDto.getCustomerId()).ifPresent(cart::setCustomer);
        }

        if (null != cartDto.getToken()) {
            cart.setToken(cartDto.getToken());
        }

        cart.setDateModified(LocalDateTime.now());
    }

    public void delete(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("The cart with provided id cannot be found!"));

        cartRepository.delete(cart);
    }
}
