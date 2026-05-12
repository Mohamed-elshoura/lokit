package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.CartItemRequest;
import com.elshoura.lokit.models.dto.request.UpdateCartItemRequest;
import com.elshoura.lokit.models.dto.response.CartResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart")
@SecurityRequirement(name = "bearerAuth")
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping
    @Operation(summary = "Get current user cart")
    public ResponseEntity<CartResponse> getMyCartApi(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(cartItemService.getMyCart(userId));
    }

    @PostMapping("/items")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<CartResponse> addCartItemApi(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,
                                                       @RequestBody CartItemRequest cartItemRequest) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok().body(cartItemService.addItem(userId, cartItemRequest));

    }

    @PutMapping("/items/{itemId}")
    @Operation(summary = "Update cart item quantity")
    public ResponseEntity<CartResponse> updateCartItemApi(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,
                                                          @PathVariable Long itemId,
                                                          @Valid @RequestBody UpdateCartItemRequest updateCartItemRequest) {
        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok().body(cartItemService.updateItem(userId, itemId, updateCartItemRequest));

    }

    @DeleteMapping("/items/{itemId}")
    @Operation(summary = "Remove cart item")
    public ResponseEntity<CartResponse> removeItemApi(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long itemId) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok().body(cartItemService.removeItem(userId, itemId));


    }
}