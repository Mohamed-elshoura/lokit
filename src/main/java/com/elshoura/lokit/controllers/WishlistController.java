package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.WishlistRequest;
import com.elshoura.lokit.models.dto.response.WishlistResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<WishlistResponse> addWishlistApi(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @Valid @RequestBody WishlistRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(wishlistService.addWishlistItem(userDetails.getUser().getId(), request));
    }

    @GetMapping
    public ResponseEntity<List<WishlistResponse>> getMyWishlist(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(wishlistService.getMyWishlist(userDetails.getUser().getId()));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromWishlistApi(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @PathVariable Long productId) {
        wishlistService.remove(userDetails.getUser().getId(), productId);
        return ResponseEntity.noContent().build();
    }

}
