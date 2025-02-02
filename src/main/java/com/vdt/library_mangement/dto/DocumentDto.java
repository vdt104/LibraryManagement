package com.vdt.library_mangement.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private String id;
    private String title;
    private String topic;
    private String description;
    private String note;
    private String category;
    private Integer yearPublished;
    private String publisher;
    private Integer quantity;
    private Set<String> authorIds;
}