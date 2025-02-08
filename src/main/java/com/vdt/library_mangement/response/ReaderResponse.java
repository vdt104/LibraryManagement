package com.vdt.library_mangement.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReaderResponse {

    @JsonProperty("full_name")
    private String fullName;

    private Date dob;

    private String gender;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String address;

    @JsonProperty("identification_number")
    private String identificationNumber;

    @NotBlank(message = "Email is required")
    private String email;

    @JsonProperty("student_id")
    private String studentId;
}
