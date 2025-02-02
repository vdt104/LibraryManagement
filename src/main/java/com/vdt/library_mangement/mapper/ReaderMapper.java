package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.DocumentCopy;

import java.util.Set;
import java.util.stream.Collectors;

public class ReaderMapper {

    public static ReaderDto toDTO(Reader reader) {
        ReaderDto dto = new ReaderDto();

        dto.setUserId(reader.getUserId());
        dto.setStudentId(reader.getStudentId());
        Set<String> documentCopyCodes = reader.getDocumentCopies().stream()
                .map(DocumentCopy::getCode)
                .collect(Collectors.toSet());
        dto.setDocumentCopyCodes(documentCopyCodes);

        return dto;
    }

    public static Reader toEntity(ReaderDto dto) {
        Reader reader = new Reader();

        reader.setUserId(dto.getUserId());
        reader.setStudentId(dto.getStudentId());
        
        return reader;
    }
}