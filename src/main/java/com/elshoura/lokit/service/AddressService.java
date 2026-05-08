package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.AddressRequest;
import com.elshoura.lokit.models.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse create(Long userId, AddressRequest request);

    List<AddressResponse> getMyAddresses(Long userId);

    AddressResponse getById(Long userId, Long addressId);

    AddressResponse update(Long userId, Long addressId, AddressRequest request);

    void delete(Long userId, Long addressId);
}
