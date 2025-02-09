package com.vdt.library_mangement.controller;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.service.ReaderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @PostMapping
    public ResponseEntity<ReaderDto> createReader(@Valid @RequestBody ReaderDto readerDto, @RequestParam(name = "expiry_period") int expiryPeriod) {
        ReaderDto createdReader = readerService.createReader(readerDto, expiryPeriod);
        return new ResponseEntity<>(createdReader, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderDto> updateReader(@PathVariable String id, @RequestBody ReaderDto readerDto) {

        ReaderDto updatedReader = readerService.updateReader(id, readerDto);
        return new ResponseEntity<>(updatedReader, HttpStatus.OK);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ReaderDto> activateReader(@PathVariable String id) {

        ReaderDto activatedReader = readerService.activateReader(id);
        return new ResponseEntity<>(activatedReader, HttpStatus.OK);
    }
}