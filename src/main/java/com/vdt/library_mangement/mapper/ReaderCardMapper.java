package com.vdt.library_mangement.mapper;

import com.vdt.library_mangement.dto.ReaderCardDto;
import com.vdt.library_mangement.entity.ReaderCard;

public class ReaderCardMapper {

    public static ReaderCardDto toDTO(ReaderCard readerCard) {
        ReaderCardDto dto = new ReaderCardDto();

        dto.setId(readerCard.getId());
        dto.setReaderId(readerCard.getReader().getUserId());
        dto.setPin(readerCard.getPin());
        dto.setIssueDate(readerCard.getIssueDate());
        dto.setExpiryDate(readerCard.getExpiryDate());
        dto.setStatus(readerCard.getStatus().name());

        return dto;
    }

    public static ReaderCard toEntity(ReaderCardDto dto) {
        ReaderCard readerCard = new ReaderCard();

        readerCard.setId(dto.getId());
        readerCard.setPin(dto.getPin());
        readerCard.setIssueDate(dto.getIssueDate());
        readerCard.setExpiryDate(dto.getExpiryDate());
        readerCard.setStatus(ReaderCard.Status.valueOf(dto.getStatus()));
        
        return readerCard;
    }
}