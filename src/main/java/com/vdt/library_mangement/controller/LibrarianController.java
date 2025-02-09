package com.vdt.library_mangement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdt.library_mangement.dto.LibrarianDto;
import com.vdt.library_mangement.response.UserResponse;
import com.vdt.library_mangement.service.LibrarianService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/librarians")
@RequiredArgsConstructor
public class LibrarianController {
    private final LibrarianService librarianService;

    @PostMapping
    public ResponseEntity<UserResponse> createLibrarian(@Valid @RequestBody LibrarianDto librarianDto) {
        UserResponse createdLibrarian = librarianService.createLibrarian(librarianDto);
        return new ResponseEntity<>(createdLibrarian, HttpStatus.CREATED);
    }
}
