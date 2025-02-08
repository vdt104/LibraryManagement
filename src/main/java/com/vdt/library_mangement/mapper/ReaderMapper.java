package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.User;

public class ReaderMapper {

    public static ReaderDto toDTO(Reader reader) {
        ReaderDto readerDto = new ReaderDto();

        readerDto.setFullName(reader.getUser().getFullName());
        readerDto.setDob(reader.getUser().getDob());
        readerDto.setGender(reader.getUser().getGender().name());
        readerDto.setPhoneNumber(reader.getUser().getPhoneNumber());
        readerDto.setAddress(reader.getUser().getAddress());
        readerDto.setIdentificationNumber(reader.getUser().getIdentificationNumber());
        readerDto.setEmail(reader.getUser().getEmail());
        readerDto.setStudentId(reader.getStudentId());

        return readerDto;
    }

    public static Reader toEntity(ReaderDto readerDto) {
        User user = new User();
        user.setFullName(readerDto.getFullName());
        user.setDob(readerDto.getDob());
        user.setGender(User.Gender.valueOf(readerDto.getGender()));
        user.setPhoneNumber(readerDto.getPhoneNumber());
        user.setAddress(readerDto.getAddress());
        user.setIdentificationNumber(readerDto.getIdentificationNumber());
        user.setEmail(readerDto.getEmail());

        Reader reader = new Reader();
        reader.setUser(user);
        reader.setStudentId(readerDto.getStudentId());

        return reader;
    }
}