package com.vdt.library_mangement.controller;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.response.ReaderResponse;
import com.vdt.library_mangement.service.ReaderService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readers")
@AllArgsConstructor
public class ReaderController {

    private ReaderService readerService;

    @PostMapping
    public ResponseEntity<ReaderResponse> createReader(@Valid @RequestBody ReaderDto readerDto) {

        ReaderResponse createdReader = readerService.createReader(readerDto);
        return new ResponseEntity<>(createdReader, HttpStatus.CREATED);
    }
}