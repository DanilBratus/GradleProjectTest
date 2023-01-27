package com.example.controller;

import com.example.model.request.CreateUserRequest;
import com.example.model.response.UserResponse;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gradle_project")
public class UserController {
    private final UserService userService;

    @Value("${spring.application.name}")
    private String nameProject;

    @GetMapping
    public String getName() {
        return nameProject;
    }

    @GetMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
    public List<UserResponse> allUsers() {
        return userService.allUsers();
    }

    @GetMapping(value = "/users/{userId}", produces = APPLICATION_JSON_VALUE)
    public UserResponse userById(@PathVariable Integer userId) {
        return userService.userById(userId);
    }

    @PostMapping(value = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponse createUser(@RequestBody CreateUserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping(value = "/users/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponse updateUser(@PathVariable Integer userId, @RequestBody CreateUserRequest userRequest) {
        return userService.update(userId, userRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/users/{userId}", produces = APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
    }
}
