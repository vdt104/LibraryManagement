package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.LibrarianDto;
import com.vdt.library_mangement.entity.Librarian;

public class LibrarianMapper {

    public static LibrarianDto toDTO(Librarian librarian) {
        LibrarianDto dto = new LibrarianDto();

        dto.setUserId(librarian.getUserId());
        dto.setDepartment(librarian.getDepartment());
        dto.setPosition(librarian.getPosition());
        dto.setWorkplace(librarian.getWorkplace());

        return dto;
    }

    public static Librarian toEntity(LibrarianDto dto) {
        Librarian librarian = new Librarian();

        librarian.setUserId(dto.getUserId());
        librarian.setDepartment(dto.getDepartment());
        librarian.setPosition(dto.getPosition());
        librarian.setWorkplace(dto.getWorkplace());
        
        return librarian;
    }
}