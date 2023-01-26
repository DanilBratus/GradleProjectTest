package com.example.service;

import com.example.model.request.CreateUserRequest;
import com.example.model.response.UserResponse;
import com.sun.istack.NotNull;

import java.util.List;

public interface UserService {
    @NotNull
    List<UserResponse> allUsers();

    @NotNull
    UserResponse userById(@NotNull Integer id);

    @NotNull
    UserResponse createUser(@NotNull CreateUserRequest request);

    @NotNull
    UserResponse update(@NotNull Integer id, @NotNull CreateUserRequest request);

    void delete(@NotNull Integer id);
}
