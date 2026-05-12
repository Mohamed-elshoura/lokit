package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.AddressRequest;
import com.elshoura.lokit.models.dto.response.AddressResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
@Tag(name = "Addresses")
@SecurityRequirement(name = "bearerAuth")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @Operation(summary = "Create address")
    public ResponseEntity<AddressResponse> create(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,
                                                  @Valid @RequestBody AddressRequest request) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(userId, request));
    }

    @GetMapping
    @Operation(summary = "Get current user addresses")
    public ResponseEntity<List<AddressResponse>> getMyAddresses(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(addressService.getMyAddresses(userId));
    }

    @GetMapping("/{addressId}")
    @Operation(summary = "Get address by ID")
    public ResponseEntity<AddressResponse> getById(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,
                                                   @PathVariable Long addressId) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(addressService.getById(userId, addressId));
    }

    @PutMapping("/{addressId}")
    @Operation(summary = "Update address")
    public ResponseEntity<AddressResponse> update(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,
                                                  @PathVariable Long addressId,
                                                  @Valid @RequestBody AddressRequest request) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(addressService.update(userId, addressId, request));
    }

    @DeleteMapping("/{addressId}")
    @Operation(summary = "Delete address")
    public ResponseEntity<Void> delete(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,
                                       @PathVariable Long addressId) {
        Long userId = userDetails.getUser().getId();
        addressService.delete(userId, addressId);
        return ResponseEntity.noContent().build();
    }
}
