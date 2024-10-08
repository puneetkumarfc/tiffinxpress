package com.tiffinXpress.controller;

import com.tiffinXpress.model.ResponseWrapper;
import com.tiffinXpress.model.Users;
import com.tiffinXpress.service.UserService;
import jakarta.persistence.PostRemove;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService usersService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody Users users){
        ResponseWrapper<Users> responseWrapper = usersService.registerUser(users);
        return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Map<String,String> loginDetails){
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");
        ResponseWrapper<Users> responseWrapper = usersService.loginUser(email,password);
        if(!responseWrapper.isStatus()){
            return new ResponseEntity<>(responseWrapper, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseWrapper<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ResponseWrapper<Object> response = new ResponseWrapper<>(false, "Validation failed", errors, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
