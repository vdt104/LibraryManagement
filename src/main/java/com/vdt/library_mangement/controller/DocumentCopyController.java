package com.vdt.library_mangement.controller;

import com.vdt.library_mangement.dto.DocumentCopyDto;
import com.vdt.library_mangement.service.DocumentCopyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/document_copies")
@RequiredArgsConstructor
public class DocumentCopyController {
    
    private final DocumentCopyService documentCopyService;

    @PostMapping("/{document_id}")
    public ResponseEntity<DocumentCopyDto> createDocumentCopy(@PathVariable("document_id") String documentId, @Valid @RequestBody DocumentCopyDto documentCopyDto) {
        DocumentCopyDto createdDocumentCopy = documentCopyService.createDocumentCopy(documentId, documentCopyDto);
        return new ResponseEntity<>(createdDocumentCopy, HttpStatus.CREATED);
    }
}
