package com.vdt.library_mangement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    @JsonProperty("document_code")
    private String documentCode;

    private String title;

    private String topic;

    private String description;

    private String note;

    private String category;

    @JsonProperty("year_published")
    private Integer yearPublished;

    private String publisher;

    private Integer quantity;

    private List<AuthorDto> authors;
}