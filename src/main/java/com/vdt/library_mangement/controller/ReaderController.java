package com.vdt.library_mangement.controller;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.ReaderService;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readers")
@AllArgsConstructor
public class ReaderController {

    private ReaderService readerService;

    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ReaderDto> createReader(@RequestBody ReaderDto readerDto) {
        Optional<User> existingUser = userRepository.findByEmail(readerDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        ReaderDto createdReader = readerService.createReader(readerDto);
        return new ResponseEntity<>(createdReader, HttpStatus.CREATED);
    }
}