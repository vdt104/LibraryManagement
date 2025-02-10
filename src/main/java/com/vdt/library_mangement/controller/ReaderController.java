package com.vdt.library_mangement.controller;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.response.UserResponse;
import com.vdt.library_mangement.service.ReaderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @PutMapping("/{id}/is_active")
    public ResponseEntity<?> activateOrDeactivateReader(@PathVariable String id, @RequestParam(name = "boolean") boolean isActive) {
        if (isActive) {
            UserResponse updatedReader = readerService.activateReader(id);
            return new ResponseEntity<>(updatedReader, HttpStatus.OK);
        } else {
            ReaderDto updatedReader = readerService.deactivateReader(id);
            return new ResponseEntity<>(updatedReader, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable String id) {
        ReaderDto reader = readerService.getReaderById(id);
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ReaderDto>> getAllReaders() {
        List<ReaderDto> readers = readerService.getAllReaders();
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @PostMapping("/{id}/bookshelf")
    public ResponseEntity<String> addDocumentToBookshelf(@PathVariable String id, @RequestParam(name = "document_copy_code") String documentCopyCode) {
        try {
            readerService.addDocumentToBookshelf(id, documentCopyCode);
            return new ResponseEntity<>("Document added to bookshelf", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}   