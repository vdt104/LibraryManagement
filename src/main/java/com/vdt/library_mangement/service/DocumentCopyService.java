package com.vdt.library_mangement.service;

import java.util.List;

import com.vdt.library_mangement.dto.DocumentCopyDto;

public interface DocumentCopyService {
    List<DocumentCopyDto> getAllDocumentCopiesOfDocument(String documentCode);

    DocumentCopyDto createDocumentCopy(String documentId, DocumentCopyDto documentCopyDto);

    DocumentCopyDto updateDocumentCopy(String documentCode, DocumentCopyDto documentCopyDto);
}
