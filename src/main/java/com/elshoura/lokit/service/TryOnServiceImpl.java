package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.dto.request.FalTryOnRequest;
import com.elshoura.lokit.models.dto.response.FalTryOnResponse;
import com.elshoura.lokit.models.dto.response.TryOnResponse;
import com.elshoura.lokit.models.entitys.Product;
import com.elshoura.lokit.repository.ProductImageRepository;
import com.elshoura.lokit.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TryOnServiceImpl implements TryOnService {


    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Value("${fal.api.key}")
    private String falApiKey;

    @Value("${fal.tryon.url}")
    private String falTryOnUrl;

    @Override
    public TryOnResponse tryOn(Long userId, MultipartFile userImage, Long productId) {

        validateImage(userImage);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        String garmentImageUrl = productImageRepository.findFirstByProductIdAndIsMainTrue(product.getId())
                .or(() -> productImageRepository.findFirstByProductIdOrderByIdAsc(product.getId()))
                .map(productImage -> productImage.getImageUrl())
                .orElseThrow(() -> new NotFoundException("Product image not found"));

        String modelImage = toDataUri(userImage);

        FalTryOnRequest request = FalTryOnRequest.builder()
                .modelImage(modelImage)
                .garmentImage(garmentImageUrl)
                .category("auto")
                .mode("balanced")
                .garmentPhotoType("auto")
                .moderationLevel("permissive")
                .numSamples(1)
                .segmentationFree(true)
                .outputFormat("png")
                .build();

        FalTryOnResponse falResponse = RestClient.create()
                .post()
                .uri(falTryOnUrl)
                .header(HttpHeaders.AUTHORIZATION, "Key " + falApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(FalTryOnResponse.class);

        if (falResponse == null
                || falResponse.images() == null
                || falResponse.images().isEmpty()
                || falResponse.images().get(0).url() == null) {
            throw new RuntimeException("Try-on generation failed");
        }

        return TryOnResponse.builder()
                .resultImageUrl(falResponse.images().get(0).url())
                .message("Try-on generated successfully")
                .build();
    }

    private void validateImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("User image is required");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.equals("image/jpeg")
                        || contentType.equals("image/png")
                        || contentType.equals("image/webp"))) {
            throw new RuntimeException("Only JPG, PNG, and WEBP images are allowed");
        }

        long maxSize = 5 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new RuntimeException("Image size must not exceed 5MB");
        }
    }

    private String toDataUri(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            return "data:" + contentType + ";base64," + base64;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read user image");
        }
    }
}
