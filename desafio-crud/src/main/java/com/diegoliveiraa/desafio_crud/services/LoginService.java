package com.diegoliveiraa.desafio_crud.services;

import com.diegoliveiraa.desafio_crud.dtos.UserDTO;
import com.diegoliveiraa.desafio_crud.entitys.User;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class LoginService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(UserService userService) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void authorizatorLogin(UserDTO userData) {
        User user = userService.findByUsername(userData.users());

        if (!passwordEncoder.matches(userData.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credential");
        }
    }

}
