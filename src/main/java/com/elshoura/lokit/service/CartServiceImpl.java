package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.entitys.Cart;
import com.elshoura.lokit.models.entitys.User;
import com.elshoura.lokit.repository.CartRepository;
import com.elshoura.lokit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.elshoura.lokit.utils.mapper.CartMapper.toCart;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

private final CartRepository cartRepository;
private final UserRepository userRepository;

   @Override
   public Cart getOrCreateCart(Long userId){

return cartRepository.findByUserId(userId).orElseGet(()->{

   User user = userRepository.findById(userId)
           .orElseThrow(()->new NotFoundException("User not found"));

   return cartRepository.save(toCart(user));

});

    }
}
