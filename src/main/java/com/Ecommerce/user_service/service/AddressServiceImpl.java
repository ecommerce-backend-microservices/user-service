package com.Ecommerce.user_service.service;



import com.Ecommerce.user_service.domain.Address;
import com.Ecommerce.user_service.domain.User;
import com.Ecommerce.user_service.dto.AddAddressRequest;
import com.Ecommerce.user_service.dto.AddressResponse;

import com.Ecommerce.user_service.repository.AddressRepository;
import com.Ecommerce.user_service.repository.UserRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository,
                              UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddressResponse addAddress(Long userId, AddAddressRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = new Address(
                request.getStreet(),
                request.getCity(),
                request.getState(),
                request.getCountry(),
                request.getPincode(),
                user
        );

        Address saved = addressRepository.save(address);
        return map(saved);
    }

    @Override
    public List<AddressResponse> getAddresses(Long userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AddressResponse updateAddress(Long userId, Long addressId,
                                         AddAddressRequest request) {

        Address address = addressRepository
                .findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        address.setDefault(false); // optional safety
        return map(address);
    }

    @Override
    public void deleteAddress(Long userId, Long addressId) {

        Address address = addressRepository
                .findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        addressRepository.delete(address);
    }

    @Override
    public void setDefaultAddress(Long userId, Long addressId) {

        List<Address> addresses = addressRepository.findByUserId(userId);

        addresses.forEach(a -> a.setDefault(a.getId().equals(addressId)));

        addressRepository.saveAll(addresses);
    }

    private AddressResponse map(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPincode(),
                address.isDefault()
        );
    }
}
