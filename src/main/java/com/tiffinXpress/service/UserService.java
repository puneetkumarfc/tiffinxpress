package com.tiffinXpress.service;

import com.tiffinXpress.model.ResponseWrapper;
import com.tiffinXpress.model.Users;
import com.tiffinXpress.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ResponseWrapper<Users> registerUser(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        Users savedUser = usersRepository.save(user);
        return new ResponseWrapper<>(true, "User created successfully", null, savedUser);
    }

    public ResponseWrapper<Users> loginUser(String email, String password){
        Users user = usersRepository.findByEmail(email);
        if(user == null){
            return new ResponseWrapper<>(false,"Email not found",null,null);
        }
        if(!encoder.matches(password, user.getPassword())){
           return new ResponseWrapper<>(false,"Invalid Credentials",null,null);
        }
        return new ResponseWrapper<>(true,"Login Successfull",null,user);
    }
}