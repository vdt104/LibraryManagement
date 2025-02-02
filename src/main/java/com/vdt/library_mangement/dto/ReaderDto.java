package com.vdt.library_mangement.dto;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReaderDto extends UserDto {
    private String studentId;
    private Set<String> documentCopyCodes;

    public ReaderDto(
        String id, String name, Date dob, String gender, String phoneNumber,
        String address, String identificationNumber, String email, String password, 
        String role, String status, String studentId, Set<String> documentCopyCodes) {
            
        super(id, name, dob, gender, phoneNumber, address, identificationNumber, email, password, role, status);
        this.studentId = studentId;
        this.documentCopyCodes = documentCopyCodes;
    }
}