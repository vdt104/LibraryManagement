package com.vdt.library_mangement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.vdt.library_mangement.dto.DocumentDto;

public interface DocumentService {
    DocumentDto createDocument(DocumentDto documentDto);

    DocumentDto getDocumentById(String id);

    Page<DocumentDto> getAllDocuments(PageRequest pageRequest);
}
