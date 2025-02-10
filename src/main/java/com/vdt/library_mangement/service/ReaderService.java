package com.vdt.library_mangement.service;

import java.util.List;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.response.UserResponse;

public interface ReaderService {
    ReaderDto getReaderById(String userId);

    List<ReaderDto> getAllReaders();

    ReaderDto createReader(ReaderDto readerDto, int expiryPeriod);

    ReaderDto updateReader(String userId, ReaderDto readerDto);

    UserResponse activateReader(String userId);

    ReaderDto deactivateReader(String userId);

    void addDocumentToBookshelf(String userId, String documentCopyCode) throws Exception;
}
