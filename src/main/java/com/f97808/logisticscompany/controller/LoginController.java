package com.f97808.logisticscompany.controller;

import com.f97808.logisticscompany.model.Authorities;
import com.f97808.logisticscompany.model.LoginUserDto;
import com.f97808.logisticscompany.service.UserLoginService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserLoginService loginService;

    LoginController(UserLoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginUser", new LoginUserDto());
        return "login";
    }

    @PostMapping("/login-processing")
    public String login(Model model, @Valid @ModelAttribute("loginUser") LoginUserDto loginUser, BindingResult result) {
        if (result.hasErrors())
            return "login";

        if (!this.loginService.loginUser(loginUser)) {
            model.addAttribute("loginError", true);
            return "login";
        }

        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            GrantedAuthority authority = (GrantedAuthority) SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0];
            switch (authority.getAuthority()) {
                case "ROLE_" + Authorities.CLIENT:
                    return "redirect:client";
                case "ROLE_" + Authorities.EMPLOYEE:
                    return "redirect:employee";
                case "ROLE_" + Authorities.ADMIN:
                    return "redirect:admin";
                default:
                    return "redirect:login";
            }
        }

        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/index";
    }

}
