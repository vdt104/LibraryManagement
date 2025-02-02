package com.vdt.library_mangement.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderRequestDto {
    private Long id;
    private Long readerId;
    private String status;
    private Date dateBorrowed;
    private Date dateDue;
    private Date dateReturned;
    private Double penaltyFee;
    private String notes;
    private Set<String> documentCopyCodes;
}
