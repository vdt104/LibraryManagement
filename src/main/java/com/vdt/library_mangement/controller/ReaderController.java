package com.vdt.library_mangement.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.dto.UserDto;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.ReaderService;
import com.vdt.library_mangement.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/reader")
@AllArgsConstructor
public class ReaderController {

    private UserRepository userRepository;
    private ReaderService readerService;
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ReaderDto> createReader(@RequestBody ReaderDto readerDto) {
        
        Optional<User> existingUser = userRepository.findByEmail(readerDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        
        readerDto.setId(UUID.randomUUID().toString());
        readerDto.setRole("READER");
        readerDto.setStatus("INACTIVE");

        // Create the user first
        UserDto createdUser = userService.createUser(readerDto);

        // Set the user ID in the reader DTO
        readerDto.setId(createdUser.getId());

        // Create the reader
        ReaderDto createdReader = readerService.createReader(readerDto);

        return new ResponseEntity<>(createdReader, HttpStatus.CREATED);
    }
}