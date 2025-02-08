package com.vdt.library_mangement.service;

import com.vdt.library_mangement.dto.DocumentCopyDto;

public interface DocumentCopyService {
    DocumentCopyDto createDocumentCopy(String documentId, DocumentCopyDto documentCopyDto);
}
