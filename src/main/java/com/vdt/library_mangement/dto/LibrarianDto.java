package com.vdt.library_mangement.dto;

import java.util.Date;

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

    public LibrarianDto(
        String id, String name, Date dob, String gender,
        String phoneNumber, String address, String identificationNumber, 
        String email, String password, String role, String status, 
        String department, String position, String workplace) {
            
        super(id, name, dob, gender, phoneNumber, address, identificationNumber, email, password, role, status);
        this.department = department;
        this.position = position;
        this.workplace = workplace;
    }
}
