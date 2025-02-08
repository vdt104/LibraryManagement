package com.vdt.library_mangement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderCardDto {

    @JsonProperty("user_id")
    private String userId;

    private String pin;

    @JsonProperty("issue_date")
    private Date issueDate;

    @JsonProperty("expiry_date")
    private Date expiryDate;

    private String status;
}