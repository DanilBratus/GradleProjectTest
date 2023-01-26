package com.example.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateAddressRequest {
    private String country;
    private String city;
    private String street;
}
