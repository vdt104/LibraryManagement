package com.vdt.library_mangement.dto;

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
    private Long documentId;
    private String location;
    private String status;
}