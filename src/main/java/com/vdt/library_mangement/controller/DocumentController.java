package com.vdt.library_mangement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdt.library_mangement.dto.DocumentDto;
import com.vdt.library_mangement.service.DocumentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDto> createDocument(@Valid @RequestBody DocumentDto documentDto) {
        DocumentDto createdDocument = documentService.createDocument(documentDto);
        return new ResponseEntity<>(createdDocument, HttpStatus.CREATED);
    }
}
