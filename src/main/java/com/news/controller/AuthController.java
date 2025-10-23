package com.news.controller;

import com.news.dto.UserRegistrationDto;
import com.news.entity.User;
import com.news.service.AuthService;
import com.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String register(@Valid UserRegistrationDto userRegistrationDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        
        try {
            User user = userService.registerUser(userRegistrationDto);
            authService.register(user);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please log in.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
    }

}
