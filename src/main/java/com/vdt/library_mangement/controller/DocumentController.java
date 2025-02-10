package com.vdt.library_mangement.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vdt.library_mangement.dto.DocumentDto;
import com.vdt.library_mangement.response.DocumentListResponse;
import com.vdt.library_mangement.response.DocumentResponse;
import com.vdt.library_mangement.service.DocumentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable String id) {
        DocumentDto document = documentService.getDocumentById(id);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }    

    @GetMapping()
    public ResponseEntity<DocumentListResponse> getAllDocuments(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("documentCode"));

        Page<DocumentDto> documents = documentService.getAllDocuments(pageRequest);

        int totalPages = documents.getTotalPages();
        List<DocumentResponse> documentList = documents.getContent().stream()
            .map(DocumentResponse::fromDocumentDto)
            .toList();

        return new ResponseEntity<>(DocumentListResponse.builder()
            .documents(documentList)
            .totalPages(totalPages)
            .build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocumentDto> createDocument(@Valid @RequestBody DocumentDto documentDto) {
        DocumentDto createdDocument = documentService.createDocument(documentDto);
        return new ResponseEntity<>(createdDocument, HttpStatus.CREATED);
    }
}
