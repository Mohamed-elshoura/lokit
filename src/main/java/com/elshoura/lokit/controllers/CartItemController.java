package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.CartItemRequest;
import com.elshoura.lokit.models.dto.request.UpdateCartItemRequest;
import com.elshoura.lokit.models.dto.response.CartResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<CartResponse> getMyCartApi(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(cartItemService.getMyCart(userId));
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addCartItemApi(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                       @RequestBody CartItemRequest cartItemRequest) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok().body(cartItemService.addItem(userId, cartItemRequest));

    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItemApi(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                          @PathVariable Long itemId,
                                                          @Valid @RequestBody UpdateCartItemRequest updateCartItemRequest) {
        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok().body(cartItemService.updateItem(userId, itemId, updateCartItemRequest));

    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> removeItemApi(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long itemId) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok().body(cartItemService.removeItem(userId, itemId));


    }
}