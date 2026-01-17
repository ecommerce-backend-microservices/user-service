package com.Ecommerce.user_service.service;



import com.Ecommerce.user_service.dto.AddAddressRequest;
import com.Ecommerce.user_service.dto.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse addAddress(Long userId, AddAddressRequest request);

    List<AddressResponse> getAddresses(Long userId);

    AddressResponse updateAddress(Long userId, Long addressId, AddAddressRequest request);

    void deleteAddress(Long userId, Long addressId);

    void setDefaultAddress(Long userId, Long addressId);
}
