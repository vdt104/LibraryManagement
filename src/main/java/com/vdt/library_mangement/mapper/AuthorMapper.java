package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.AuthorDto;
import com.vdt.library_mangement.entity.Author;

public class AuthorMapper {

    public static AuthorDto toDTO(Author author) {
        AuthorDto dto = new AuthorDto();

        dto.setId(author.getId());
        dto.setName(author.getName());
        
        return dto;
    }

    public static Author toEntity(AuthorDto dto) {
        Author author = new Author();

        author.setId(dto.getId());
        author.setName(dto.getName());

        return author;
    }
}