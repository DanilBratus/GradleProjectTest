package com.example.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserRequest {
    private String login;
    private String firstName;
    private String secondName;
    private Integer age;
    private CreateAddressRequest address;
}
