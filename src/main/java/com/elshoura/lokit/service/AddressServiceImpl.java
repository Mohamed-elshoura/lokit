package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.errors.exception.UserForbiddenException;
import com.elshoura.lokit.models.dto.request.AddressRequest;
import com.elshoura.lokit.models.dto.response.AddressResponse;
import com.elshoura.lokit.models.entitys.Address;
import com.elshoura.lokit.models.entitys.User;
import com.elshoura.lokit.repository.AddressRepository;
import com.elshoura.lokit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public AddressResponse create(Long userId, AddressRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Address address = Address.builder()
                .user(user)
                .country(request.getCountry().trim())
                .city(request.getCity().trim())
                .street(request.getStreet().trim())
                .zipCode(request.getZipCode())
                .governorate(request.getGovernorate())
                .build();

        return map(addressRepository.save(address));
    }

    @Override
    public List<AddressResponse> getMyAddresses(Long userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AddressResponse getById(Long userId, Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found"));

        if (!address.getUser().getId().equals(userId)) {
            throw new UserForbiddenException("Address does not belong to this user");
        }

        return map(address);
    }

    @Override
    public AddressResponse update(Long userId, Long addressId, AddressRequest request) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found"));

        if (!address.getUser().getId().equals(userId)) {
            throw new UserForbiddenException("Address does not belong to this user");
        }

        address.setCountry(request.getCountry().trim());
        address.setCity(request.getCity().trim());
        address.setStreet(request.getStreet().trim());
        address.setZipCode(request.getZipCode());
        address.setGovernorate(request.getGovernorate());

        return map(addressRepository.save(address));
    }

    @Override
    public void delete(Long userId, Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found"));

        if (!address.getUser().getId().equals(userId)) {
            throw new UserForbiddenException("Address does not belong to this user");
        }

        addressRepository.delete(address);
    }

    private AddressResponse map(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .governorate(address.getGovernorate())
                .build();
    }
}
