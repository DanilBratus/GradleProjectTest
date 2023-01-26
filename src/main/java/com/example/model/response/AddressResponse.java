package com.example.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressResponse {
    private String country;
    private String city;
    private String street;
}
