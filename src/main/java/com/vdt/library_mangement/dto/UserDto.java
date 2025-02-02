package com.vdt.library_mangement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private Date dob;
    private String gender;
    private String phoneNumber;
    private String address;
    private String identificationNumber;
    private String email;
    private String password;
    private String role;
    private String status;
}