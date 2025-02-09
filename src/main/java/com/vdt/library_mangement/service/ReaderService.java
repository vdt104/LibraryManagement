package com.vdt.library_mangement.service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.response.UserResponse;

public interface ReaderService {
    ReaderDto createReader(ReaderDto readerDto, int expiryPeriod);

    ReaderDto updateReader(String userId, ReaderDto readerDto);

    UserResponse activateReader(String userId);

    ReaderDto deactivateReader(String userId);
}
