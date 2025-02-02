package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.DocumentDto;
import com.vdt.library_mangement.entity.Document;
import com.vdt.library_mangement.entity.Author;

import java.util.Set;
import java.util.stream.Collectors;

public class DocumentMapper {

    public static DocumentDto toDTO(Document document) {
        DocumentDto dto = new DocumentDto();

        dto.setId(document.getId());
        dto.setTitle(document.getTitle());
        dto.setTopic(document.getTopic());
        dto.setDescription(document.getDescription());
        dto.setNote(document.getNote());
        dto.setCategory(document.getCategory());
        dto.setYearPublished(document.getYearPublished());
        dto.setPublisher(document.getPublisher());
        dto.setQuantity(document.getQuantity());
        Set<Long> authorIds = document.getAuthors().stream()
                .map(Author::getId)
                .collect(Collectors.toSet());
        dto.setAuthorIds(authorIds);

        return dto;
    }

    public static Document toEntity(DocumentDto dto) {
        Document document = new Document();
        
        document.setId(dto.getId());
        document.setTitle(dto.getTitle());
        document.setTopic(dto.getTopic());
        document.setDescription(dto.getDescription());
        document.setNote(dto.getNote());
        document.setCategory(dto.getCategory());
        document.setYearPublished(dto.getYearPublished());
        document.setPublisher(dto.getPublisher());
        document.setQuantity(dto.getQuantity());

        return document;
    }
}