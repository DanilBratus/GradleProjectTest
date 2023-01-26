package com.example.service;

import com.example.model.request.CreateAddressRequest;
import com.example.model.request.CreateUserRequest;
import com.example.model.response.AddressResponse;
import com.example.model.response.UserResponse;
import com.example.model.Address;
import com.example.model.User;
import com.example.repositories.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private static UserRepository repository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> allUsers() {
        return repository.findAll()
                .stream()
                .map(this ::buildResponse)
                .collect(Collectors.toList());
    }

    private UserResponse buildResponse(@NotNull User user) {
        return new UserResponse()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setAge(user.getAge())
                .setFirstName(user.getFirstName())
                .setSecondName(user.getSecondName())
                .setAddress(new AddressResponse()
                        .setCountry(user.getAddress().getCountry())
                        .setCity(user.getAddress().getCity())
                        .setStreet(user.getAddress().getStreet()));
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public UserResponse userById(@NotNull Integer id) {
        return repository.findById(id)
                .map(this::buildResponse)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + "is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public UserResponse createUser(@NotNull CreateUserRequest request) {
        User user = buildRequest(request);
        return buildResponse(repository.save(user));
    }

    private User buildRequest(@NotNull CreateUserRequest request) {
        return new User()
                .setLogin(request.getLogin())
                .setAge(request.getAge())
                .setFirstName(request.getFirstName())
                .setSecondName(request.getSecondName())
                .setAddress(new Address()
                        .setCountry(request.getAddress().getCountry())
                        .setCity(request.getAddress().getCity())
                        .setStreet(request.getAddress().getStreet()));
    }

    @NotNull
    @Override
    @Transactional
    public UserResponse update(@NotNull Integer id, @NotNull CreateUserRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " is not found"));
        updateUser(user, request);
        return buildResponse(repository.save(user));
    }

    private void updateUser(@NotNull User user, @NotNull CreateUserRequest request) {
        user.setLogin(request.getLogin());
        user.setFirstName(request.getFirstName());
        user.setSecondName(request.getSecondName());
        user.setAge(request.getAge());

        CreateAddressRequest address = request.getAddress();
        if (address != null) {
            user.getAddress().setCountry(address.getCountry());
            user.getAddress().setCity(address.getCity());
            user.getAddress().setStreet(address.getStreet());
        }
    }

    @Override
    @Transactional
    public void delete(@NotNull Integer id) {
        repository.deleteById(id);
    }
}
