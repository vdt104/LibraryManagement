package com.vdt.library_mangement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LibrarianDto extends UserDto {
    private String department;
    private String position;
    private String workplace;
}
