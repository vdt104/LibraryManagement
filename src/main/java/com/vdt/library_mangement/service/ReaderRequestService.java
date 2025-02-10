package com.vdt.library_mangement.service;

import com.vdt.library_mangement.dto.ReaderRequestDto;

public interface ReaderRequestService {
    ReaderRequestDto createReaderRequest(ReaderRequestDto readerRequestDto);

    ReaderRequestDto getReaderRequestById(String id);

    ReaderRequestDto acceptReaderRequest(String id, ReaderRequestDto readerRequestDto);

    ReaderRequestDto rejectReaderRequest(String id, ReaderRequestDto readerRequestDto);

    ReaderRequestDto borrowReaderRequest(String id, ReaderRequestDto readerRequestDto);

    ReaderRequestDto returnReaderRequest(String id, ReaderRequestDto readerRequestDto);

    ReaderRequestDto cancelReaderRequest(String id, ReaderRequestDto readerRequestDto);
}
