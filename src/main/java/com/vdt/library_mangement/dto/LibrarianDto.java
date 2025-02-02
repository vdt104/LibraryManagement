package com.vdt.library_mangement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianDto {
    private Long userId;
    private String department;
    private String position;
    private String workplace;
}
