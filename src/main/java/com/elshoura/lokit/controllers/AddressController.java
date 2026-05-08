package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.AddressRequest;
import com.elshoura.lokit.models.dto.response.AddressResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> create(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                  @Valid @RequestBody AddressRequest request) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getMyAddresses(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(addressService.getMyAddresses(userId));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getById(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                   @PathVariable Long addressId) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(addressService.getById(userId, addressId));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> update(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                  @PathVariable Long addressId,
                                                  @Valid @RequestBody AddressRequest request) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(addressService.update(userId, addressId, request));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @PathVariable Long addressId) {
        Long userId = userDetails.getUser().getId();
        addressService.delete(userId, addressId);
        return ResponseEntity.noContent().build();
    }
}
