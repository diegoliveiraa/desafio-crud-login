package com.diegoliveiraa.desafio_crud.repositories;

import com.diegoliveiraa.desafio_crud.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsers(String users);
}
