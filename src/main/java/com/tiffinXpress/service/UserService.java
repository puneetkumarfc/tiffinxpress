package com.tiffinXpress.service;

import com.tiffinXpress.model.Users;
import com.tiffinXpress.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public void registerUser(Users user){
        usersRepository.save(user);
    }
}