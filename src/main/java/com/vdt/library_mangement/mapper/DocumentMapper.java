package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.DocumentDto;
import com.vdt.library_mangement.entity.Document;
import com.vdt.library_mangement.dto.AuthorDto;

import java.util.stream.Collectors;

public class DocumentMapper {
    public static DocumentDto toDTO(Document document) {
        DocumentDto documentDto = new DocumentDto();

        documentDto.setDocumentCode(document.getDocumentCode());
        documentDto.setTitle(document.getTitle());
        documentDto.setTopic(document.getTopic());
        documentDto.setDescription(document.getDescription());
        documentDto.setNote(document.getNote());
        documentDto.setCategory(document.getCategory().getCode());
        documentDto.setYearPublished(document.getYearPublished());
        documentDto.setPublisher(document.getPublisher());
        documentDto.setQuantity(document.getQuantity());
        documentDto.setAuthors(document.getAuthors().stream()
                .map(author -> new AuthorDto(author.getId(), author.getName()))
                .collect(Collectors.toList()));

        return documentDto;
    }

    public static Document toEntity(DocumentDto documentDto) {
        Document document = new Document();

        document.setDocumentCode(documentDto.getDocumentCode());
        document.setTitle(documentDto.getTitle());
        document.setTopic(documentDto.getTopic());
        document.setDescription(documentDto.getDescription());
        document.setNote(documentDto.getNote());
        document.setYearPublished(documentDto.getYearPublished());
        document.setPublisher(documentDto.getPublisher());
        document.setQuantity(documentDto.getQuantity());

        return document;
    }
}