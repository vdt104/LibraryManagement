package com.vdt.library_mangement.service;

import com.vdt.library_mangement.dto.ReaderDto;

public interface ReaderService {
    ReaderDto createReader(ReaderDto readerDto, int expiryPeriod);
    
    ReaderDto updateReader(String userId, ReaderDto readerDto);

    ReaderDto activateReader(String userId);
}
