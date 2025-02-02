package com.vdt.library_mangement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    
    private final UserRepository userRepository;
    private UserService userService;
    
}
