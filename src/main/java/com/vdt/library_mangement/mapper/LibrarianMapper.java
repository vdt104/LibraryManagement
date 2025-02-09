package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.LibrarianDto;
import com.vdt.library_mangement.entity.Librarian;
import com.vdt.library_mangement.entity.User;

public class LibrarianMapper {

    public static LibrarianDto toDTO(Librarian librarian) {
        LibrarianDto librarianDto = new LibrarianDto();

        librarianDto.setFullName(librarian.getUser().getFullName());
        librarianDto.setDob(librarian.getUser().getDob());
        librarianDto.setGender(librarian.getUser().getGender().name());
        librarianDto.setPhoneNumber(librarian.getUser().getPhoneNumber());
        librarianDto.setAddress(librarian.getUser().getAddress());
        librarianDto.setIdentificationNumber(librarian.getUser().getIdentificationNumber());
        librarianDto.setEmail(librarian.getUser().getEmail());
        librarianDto.setDepartment(librarian.getDepartment());
        librarianDto.setPosition(librarian.getPosition());
        librarianDto.setWorkplace(librarian.getWorkplace());

        return librarianDto;
    }

    public static Librarian toEntity(LibrarianDto librarianDto) {
        User user = new User();
        user.setFullName(librarianDto.getFullName());
        user.setDob(librarianDto.getDob());
        user.setGender(User.Gender.valueOf(librarianDto.getGender()));
        user.setPhoneNumber(librarianDto.getPhoneNumber());
        user.setAddress(librarianDto.getAddress());
        user.setIdentificationNumber(librarianDto.getIdentificationNumber());
        user.setEmail(librarianDto.getEmail());

        Librarian librarian = new Librarian();
        librarian.setUser(user);
        librarian.setDepartment(librarianDto.getDepartment());
        librarian.setPosition(librarianDto.getPosition());
        librarian.setWorkplace(librarianDto.getWorkplace());

        return librarian;
    }
}