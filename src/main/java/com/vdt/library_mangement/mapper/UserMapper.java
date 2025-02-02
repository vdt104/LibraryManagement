package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.UserDto;
import com.vdt.library_mangement.entity.User;

public class UserMapper {

    public static UserDto toDTO(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setDob(user.getDob());
        dto.setGender(user.getGender().name());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        dto.setIdentificationNumber(user.getIdentificationNumber());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole().name());
        dto.setStatus(user.getStatus().name());

        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setDob(dto.getDob());
        user.setGender(User.Gender.valueOf(dto.getGender()));
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setIdentificationNumber(dto.getIdentificationNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(User.Role.valueOf(dto.getRole()));
        user.setStatus(User.Status.valueOf(dto.getStatus()));
        
        return user;
    }
}