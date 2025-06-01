package com.kwizera.springbootamalitechlab09taskmanager.controllers;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.UserSignInDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.UserSignUpDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Employee;
import com.kwizera.springbootamalitechlab09taskmanager.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User Controller", description = "This controller exposes endpoints for all necessary operations regarding a user")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @Operation(summary = "Creates/Signs-in a user")
    @PostMapping
    public ResponseEntity<UserSignInDTO> initializeSession(@RequestBody UserSignUpDTO userInfo) throws InvalidInputException {
        Employee createdUser = userServices.signIn(new Employee(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getEmail()));
        UserSignInDTO userSignInDTO = new UserSignInDTO(createdUser.getLastName() + " " + createdUser.getFirstName(), createdUser.getEmail());
        return new ResponseEntity<>(userSignInDTO, HttpStatus.CREATED);
    }
}
