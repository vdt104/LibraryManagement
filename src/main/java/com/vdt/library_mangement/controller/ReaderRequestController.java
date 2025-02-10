package com.vdt.library_mangement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<ReaderRequestDto> getReaderRequest(@PathVariable("id") String id) {
        ReaderRequestDto readerRequest = readerRequestService.getReaderRequestById(id);
        return new ResponseEntity<>(readerRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReaderRequestDto> createReaderRequest(@Valid @RequestBody ReaderRequestDto readerRequestDto) {
        ReaderRequestDto createdReaderRequest = readerRequestService.createReaderRequest(readerRequestDto);
        return new ResponseEntity<>(createdReaderRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ReaderRequestDto> updateReaderRequest(@PathVariable("id") String id, @Valid @RequestBody ReaderRequestDto readerRequestDto) {
        ReaderRequestDto updatedReaderRequest;

        if (readerRequestDto.getStatus().equals("ACCEPTED")) {
            updatedReaderRequest = readerRequestService.acceptReaderRequest(id, readerRequestDto);
        } else if (readerRequestDto.getStatus().equals("REJECTED")) {
            updatedReaderRequest = readerRequestService.rejectReaderRequest(id, readerRequestDto);
        } else if (readerRequestDto.getStatus().equals("BORROWED")) {
            updatedReaderRequest = readerRequestService.borrowReaderRequest(id, readerRequestDto);
        } else if (readerRequestDto.getStatus().equals("RETURNED")) {
            updatedReaderRequest = readerRequestService.returnReaderRequest(id, readerRequestDto);
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
  
        return new ResponseEntity<>(updatedReaderRequest, HttpStatus.OK);
    }

    @PutMapping("/{id}/action=cancel")
    public ResponseEntity<ReaderRequestDto> cancelReaderRequest(@PathVariable("id") String id, @Valid @RequestBody ReaderRequestDto readerRequestDto) {
        ReaderRequestDto updatedReaderRequest = readerRequestService.cancelReaderRequest(id, readerRequestDto);
        return new ResponseEntity<>(updatedReaderRequest, HttpStatus.OK);
    }
}
