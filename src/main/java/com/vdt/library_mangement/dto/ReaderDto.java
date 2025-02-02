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
public class ReaderDto {
    private Long userId;
    private String studentId;
    private Set<String> documentCopyCodes;
}
