package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.WishlistRequest;
import com.elshoura.lokit.models.dto.response.WishlistResponse;

import java.util.List;

public interface WishlistService {


    WishlistResponse addWishlistItem(Long userId, WishlistRequest request);

    List<WishlistResponse> getMyWishlist(Long userId);

    void remove(Long userId, Long productId);
}
