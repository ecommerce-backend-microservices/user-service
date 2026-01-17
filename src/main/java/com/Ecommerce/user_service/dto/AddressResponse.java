package com.Ecommerce.user_service.dto;



public class AddressResponse {

    private final Long addressId;
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String pincode;
    private final boolean isDefault;

    public AddressResponse(Long addressId, String street, String city,
                           String state, String country,
                           String pincode, boolean isDefault) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.isDefault = isDefault;
    }

    public Long getAddressId() { return addressId; }
    public boolean isDefault() { return isDefault; }
}
