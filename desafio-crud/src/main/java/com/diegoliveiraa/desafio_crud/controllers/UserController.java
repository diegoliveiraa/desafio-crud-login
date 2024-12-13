package com.diegoliveiraa.desafio_crud.controllers;

import com.diegoliveiraa.desafio_crud.dtos.UserDTO;
import com.diegoliveiraa.desafio_crud.entitys.User;
import com.diegoliveiraa.desafio_crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserDTO userDTO) {
        System.out.println("Tentando criar usuario");
        userService.createUser(userDTO);
        return "redirect:/login"; // Redireciona para a página de login
    }

}
