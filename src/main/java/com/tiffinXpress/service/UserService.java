package com.tiffinXpress.service;

import com.tiffinXpress.model.ResponseWrapper;
import com.tiffinXpress.model.Users;
import com.tiffinXpress.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public ResponseWrapper<Users> registerUser(Users user){
        Users savedUser = usersRepository.save(user);
        return new ResponseWrapper<>(true, "User created successfully", null, savedUser);
    }
}