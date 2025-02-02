package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.ReaderRequestDto;
import com.vdt.library_mangement.entity.ReaderRequest;
import com.vdt.library_mangement.entity.DocumentCopy;

import java.util.Set;
import java.util.stream.Collectors;

public class ReaderRequestMapper {

    public static ReaderRequestDto toDTO(ReaderRequest readerRequest) {
        ReaderRequestDto dto = new ReaderRequestDto();

        dto.setId(readerRequest.getId());
        dto.setReaderId(readerRequest.getReader().getUserId());
        dto.setStatus(readerRequest.getStatus().name());
        dto.setDateBorrowed(readerRequest.getDateBorrowed());
        dto.setDateDue(readerRequest.getDateDue());
        dto.setDateReturned(readerRequest.getDateReturned());
        dto.setPenaltyFee(readerRequest.getPenaltyFee());
        dto.setNotes(readerRequest.getNotes());
        Set<String> documentCopyCodes = readerRequest.getDocumentCopies().stream()
                .map(DocumentCopy::getCode)
                .collect(Collectors.toSet());
        dto.setDocumentCopyCodes(documentCopyCodes);

        return dto;
    }

    public static ReaderRequest toEntity(ReaderRequestDto dto) {
        ReaderRequest readerRequest = new ReaderRequest();

        readerRequest.setId(dto.getId());
        readerRequest.setStatus(ReaderRequest.Status.valueOf(dto.getStatus()));
        readerRequest.setDateBorrowed(dto.getDateBorrowed());
        readerRequest.setDateDue(dto.getDateDue());
        readerRequest.setDateReturned(dto.getDateReturned());
        readerRequest.setPenaltyFee(dto.getPenaltyFee());
        readerRequest.setNotes(dto.getNotes());
        
        return readerRequest;
    }
}