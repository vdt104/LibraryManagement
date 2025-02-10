package com.vdt.library_mangement.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vdt.library_mangement.dto.AuthorDto;
import com.vdt.library_mangement.dto.DocumentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentResponse {
    @JsonProperty("document_code")
    private String documentCode;
    private String title;
    private List<AuthorDto> author;
    private String publisher;

    public static DocumentResponse fromDocumentDto(DocumentDto documentDto) {
        return DocumentResponse.builder()
            .documentCode(documentDto.getDocumentCode())
            .title(documentDto.getTitle())
            .author(documentDto.getAuthors())
            .publisher(documentDto.getPublisher())
            .build();
    }
}
