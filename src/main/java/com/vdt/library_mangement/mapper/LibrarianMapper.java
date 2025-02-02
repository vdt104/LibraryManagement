package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.LibrarianDto;
import com.vdt.library_mangement.entity.Librarian;
import com.vdt.library_mangement.entity.User;

public class LibrarianMapper {

    public static LibrarianDto toDTO(Librarian librarian) {
        LibrarianDto dto = new LibrarianDto();

        dto.setId(librarian.getUserId());
        dto.setName(librarian.getUser().getName());
        dto.setDob(librarian.getUser().getDob());
        dto.setGender(librarian.getUser().getGender().name());
        dto.setPhoneNumber(librarian.getUser().getPhoneNumber());
        dto.setAddress(librarian.getUser().getAddress());
        dto.setIdentificationNumber(librarian.getUser().getIdentificationNumber());
        dto.setEmail(librarian.getUser().getEmail());
        dto.setRole(librarian.getUser().getRole().name());
        dto.setStatus(librarian.getUser().getStatus().name());
        dto.setDepartment(librarian.getDepartment());
        dto.setPosition(librarian.getPosition());
        dto.setWorkplace(librarian.getWorkplace());

        return dto;
    }

    public static Librarian toEntity(LibrarianDto dto) {
        Librarian librarian = new Librarian();

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setDob(dto.getDob());
        if (dto.getGender() != null) {
            user.setGender(User.Gender.valueOf(dto.getGender()));
        }
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setIdentificationNumber(dto.getIdentificationNumber());
        user.setEmail(dto.getEmail());
        if (dto.getRole() != null) {
            user.setRole(User.Role.valueOf(dto.getRole()));
        }
        if (dto.getStatus() != null) {
            user.setStatus(User.Status.valueOf(dto.getStatus()));
        }

        librarian.setUser(user);
        librarian.setUserId(dto.getId());
        librarian.setDepartment(dto.getDepartment());
        librarian.setPosition(dto.getPosition());
        librarian.setWorkplace(dto.getWorkplace());

        return librarian;
    }
}