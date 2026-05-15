package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.response.TryOnResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.TryOnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@Tag(name = "AI Try-On", description = "Virtual try-on APIs using FAL AI")
@SecurityRequirement(name = "bearerAuth")
public class TryOnController {

    private final TryOnService tryOnService;

    @Operation(summary = "Try on product using user image")
    @PostMapping(value = "/try-on", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TryOnResponse> tryOn(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,

            @RequestPart("userImage") MultipartFile userImage,

            @RequestParam Long productId
    ) {
        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(
                tryOnService.tryOn(userId, userImage, productId)
        );
    }
}
