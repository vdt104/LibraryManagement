package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.entity.DocumentCopy;

import java.util.Set;
import java.util.stream.Collectors;

public class ReaderMapper {

    public static ReaderDto toDTO(Reader reader) {
        ReaderDto dto = new ReaderDto();

        dto.setId(reader.getUserId());
        dto.setName(reader.getUser().getName());
        dto.setDob(reader.getUser().getDob());
        dto.setGender(reader.getUser().getGender().name());
        dto.setPhoneNumber(reader.getUser().getPhoneNumber());
        dto.setAddress(reader.getUser().getAddress());
        dto.setIdentificationNumber(reader.getUser().getIdentificationNumber());
        dto.setEmail(reader.getUser().getEmail());
        dto.setRole(reader.getUser().getRole().name());
        dto.setStatus(reader.getUser().getStatus().name());
        dto.setStudentId(reader.getStudentId());

        if (reader.getDocumentCopies() != null) {
            Set<String> documentCopyCodes = reader.getDocumentCopies().stream()
                    .map(DocumentCopy::getCode)
                    .collect(Collectors.toSet());
            dto.setDocumentCopyCodes(documentCopyCodes);
        }

        return dto;
    }

    public static Reader toEntity(ReaderDto dto) {
        Reader reader = new Reader();

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

        reader.setUser(user);
        reader.setUserId(dto.getId());
        reader.setStudentId(dto.getStudentId());

        return reader;
    }
}