package com.Ecommerce.user_service.controller;



import com.Ecommerce.user_service.dto.AddAddressRequest;
import com.Ecommerce.user_service.dto.AddressResponse;
import com.Ecommerce.user_service.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse addAddress(
            @PathVariable Long userId,
            @Valid @RequestBody AddAddressRequest request) {
        return addressService.addAddress(userId, request);
    }

    @GetMapping
    public List<AddressResponse> getAddresses(@PathVariable Long userId) {
        return addressService.getAddresses(userId);
    }

    @PutMapping("/{addressId}")
    public AddressResponse updateAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId,
            @Valid @RequestBody AddAddressRequest request) {
        return addressService.updateAddress(userId, addressId, request);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId) {
        addressService.deleteAddress(userId, addressId);
    }

    @PutMapping("/{addressId}/default")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setDefaultAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId) {
        addressService.setDefaultAddress(userId, addressId);
    }
}
