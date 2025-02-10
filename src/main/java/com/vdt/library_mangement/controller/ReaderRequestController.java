package com.vdt.library_mangement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdt.library_mangement.dto.ReaderRequestDto;
import com.vdt.library_mangement.service.ReaderRequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/reader_requests")
@RequiredArgsConstructor
public class ReaderRequestController {
    private final ReaderRequestService readerRequestService;

    @PostMapping
    public ResponseEntity<ReaderRequestDto> createReaderRequest(@Valid @RequestBody ReaderRequestDto readerRequestDto) {
        ReaderRequestDto createdReaderRequest = readerRequestService.createReaderRequest(readerRequestDto);
        return new ResponseEntity<>(createdReaderRequest, HttpStatus.CREATED);
    }
}
