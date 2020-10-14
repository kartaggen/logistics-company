package com.f97808.logisticscompany.controller;

import com.f97808.logisticscompany.model.UserDto;
import com.f97808.logisticscompany.service.UserRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserRegistrationService registrationService;

    RegisterController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        this.registrationService.createUser(user);

        return "redirect:register?success";
    }

}
