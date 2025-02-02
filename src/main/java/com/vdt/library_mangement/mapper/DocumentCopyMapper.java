package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.DocumentCopyDto;
import com.vdt.library_mangement.entity.DocumentCopy;

public class DocumentCopyMapper {

    public static DocumentCopyDto toDTO(DocumentCopy documentCopy) {
        DocumentCopyDto dto = new DocumentCopyDto();

        dto.setCode(documentCopy.getCode());
        dto.setDocumentId(documentCopy.getDocument().getId());
        dto.setLocation(documentCopy.getLocation());
        dto.setStatus(documentCopy.getStatus().name());

        return dto;
    }

    public static DocumentCopy toEntity(DocumentCopyDto dto) {
        DocumentCopy documentCopy = new DocumentCopy();

        documentCopy.setCode(dto.getCode());
        documentCopy.setLocation(dto.getLocation());
        documentCopy.setStatus(DocumentCopy.Status.valueOf(dto.getStatus()));
        
        return documentCopy;
    }
}