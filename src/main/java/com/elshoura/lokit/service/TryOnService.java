package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.response.TryOnResponse;
import org.springframework.web.multipart.MultipartFile;

public interface TryOnService {
    TryOnResponse tryOn(Long userId, MultipartFile userImage, Long productId);

}
