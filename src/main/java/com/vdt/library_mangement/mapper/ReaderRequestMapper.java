package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.ReaderRequestDto;
import com.vdt.library_mangement.entity.ReaderRequest;

public class ReaderRequestMapper {

    public static ReaderRequestDto toDTO(ReaderRequest readerRequest) {
        ReaderRequestDto readerRequestDto = new ReaderRequestDto();

        readerRequestDto.setUserId(readerRequest.getReader().getUserId());
        readerRequestDto.setStatus(readerRequest.getStatus().name());
        readerRequestDto.setDateBorrowed(readerRequest.getDateBorrowed());
        readerRequestDto.setBorrowingPeriod(readerRequest.getBorrowingPeriod());
        readerRequestDto.setDateReturned(readerRequest.getDateReturned());
        readerRequestDto.setPenaltyFee(readerRequest.getPenaltyFee());
        readerRequestDto.setNotes(readerRequest.getNotes());

        return readerRequestDto;
    }

    public static ReaderRequest toEntity(ReaderRequestDto readerRequestDto) {
        ReaderRequest readerRequest = new ReaderRequest();

        readerRequest.setStatus(ReaderRequest.Status.valueOf(readerRequestDto.getStatus()));
        readerRequest.setDateBorrowed(readerRequestDto.getDateBorrowed());
        readerRequest.setBorrowingPeriod(readerRequestDto.getBorrowingPeriod());
        readerRequest.setDateReturned(readerRequestDto.getDateReturned());
        readerRequest.setPenaltyFee(readerRequestDto.getPenaltyFee());
        readerRequest.setNotes(readerRequestDto.getNotes());

        return readerRequest;
    }
}