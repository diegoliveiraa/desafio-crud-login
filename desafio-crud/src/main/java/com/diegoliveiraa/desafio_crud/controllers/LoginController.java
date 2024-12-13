package com.diegoliveiraa.desafio_crud.controllers;

import com.diegoliveiraa.desafio_crud.dtos.UserDTO;
import com.diegoliveiraa.desafio_crud.services.LoginService;
import com.diegoliveiraa.desafio_crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            loginService.authorizatorLogin(userDTO);

            model.addAttribute("message", "Login successful!");
            return "redirect:/students"; // Redireciona para /students
        } catch (RuntimeException ex) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Retorna para a página de login
        }
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Exibe a página de login
    }
}
