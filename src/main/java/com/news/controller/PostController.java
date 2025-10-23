package com.news.controller;

import com.news.dto.PostCreateDto;
import com.news.entity.User;
import com.news.exception.BusinessException;
import com.news.service.PostService;
import com.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        return "post/create";
    }

    @PostMapping
    public String createPost(@Valid PostCreateDto postCreateDto, 
                           BindingResult bindingResult,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return "post/create";
        }
        
        try {
            User currentUser = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new BusinessException("User not found"));
            
            postService.createPost(postCreateDto, currentUser);
            redirectAttributes.addFlashAttribute("successMessage", "Post created successfully!");
            return "redirect:/";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create post. Please try again.");
            return "redirect:/posts/create";
        }
    }
}
