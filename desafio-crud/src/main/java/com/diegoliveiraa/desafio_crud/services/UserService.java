package com.diegoliveiraa.desafio_crud.services;

import com.diegoliveiraa.desafio_crud.dtos.UserDTO;
import com.diegoliveiraa.desafio_crud.entitys.User;
import com.diegoliveiraa.desafio_crud.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public User createUser(UserDTO createData) {
        User newUser = new User();
        newUser.setUsers(createData.users());
        newUser.setPassword(passwordEncoder.encode(createData.password()));
        System.out.println(newUser);

        System.out.println("Usuario criado" + newUser);

        this.repository.save(newUser);
        return newUser;
    }


    public List<User> getAllUser() {
        return this.repository.findAll();
    }


    public User updateUser(UserDTO updateData) {
        User user = this.repository.findById(updateData.id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsers(updateData.users());


        if (updateData.password() != null && !updateData.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateData.password()));
        }

        this.repository.save(user);
        return user;
    }


    public void deleteUser(Long id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        this.repository.delete(user);
    }


    public User findByUsername(String username) {
        return this.repository.findByUsers(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
