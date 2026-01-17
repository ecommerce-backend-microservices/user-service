package com.Ecommerce.user_service.dto;


import jakarta.validation.constraints.NotBlank;

public class AddAddressRequest {

    @NotBlank private String street;
    @NotBlank private String city;
    @NotBlank private String state;
    @NotBlank private String country;
    @NotBlank private String pincode;

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPincode() { return pincode; }
}
