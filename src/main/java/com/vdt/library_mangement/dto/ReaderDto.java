package com.vdt.library_mangement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReaderDto extends UserDto {

    @JsonProperty("student_id")
    private String studentId;
}