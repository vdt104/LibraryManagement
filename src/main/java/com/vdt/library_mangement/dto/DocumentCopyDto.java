package com.vdt.library_mangement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCopyDto {
    private String code;

    @JsonProperty("document_id")
    @JsonIgnore
    private String documentId;

    private String location;
    
    private String status;
}