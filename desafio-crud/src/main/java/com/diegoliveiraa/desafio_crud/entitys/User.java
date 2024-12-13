package com.diegoliveiraa.desafio_crud.entitys;

import com.diegoliveiraa.desafio_crud.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity(name = "users")
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String users;

    private String password;

    public User(UserDTO data) {
        this.users = data.users();
        this.password = data.password();
    }

    public User(Long id, String users, String password) {
        this.id = id;
        this.users = users;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
