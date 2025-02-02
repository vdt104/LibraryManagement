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
public class ReaderCardDto {
    private Long id;
    private Long readerId;
    private String pin;
    private Date issueDate;
    private Date expiryDate;
    private String status;
}