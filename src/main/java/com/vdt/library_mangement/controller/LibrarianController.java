package com.vdt.library_mangement.controller;

import com.vdt.library_mangement.dto.LibrarianDto;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.LibrarianService;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/librarians")
@AllArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<LibrarianDto> createLibrarian(@RequestBody LibrarianDto librarianDto) {
        Optional<User> existingUser = userRepository.findByEmail(librarianDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        LibrarianDto createdLibrarian = librarianService.createLibrarian(librarianDto);
        return new ResponseEntity<>(createdLibrarian, HttpStatus.CREATED);
    }
}