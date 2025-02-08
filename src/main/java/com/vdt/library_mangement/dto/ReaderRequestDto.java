package com.vdt.library_mangement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderRequestDto {
    
    @JsonProperty("user_id")
    private String userId;

    private String status;

    @JsonProperty("date_borrowed")
    private Date dateBorrowed;

    @JsonProperty("date_due")
    private Date dateDue;

    @JsonProperty("date_returned")
    private Date dateReturned;

    @JsonProperty("penalty_fee")
    @Min(value = 0, message = "Penalty fee must be greater than or equal to 0")
    private Double penaltyFee;

    private String notes;
}
