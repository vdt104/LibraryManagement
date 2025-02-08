package com.vdt.library_mangement.service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.response.ReaderResponse;

public interface ReaderService {
    ReaderResponse createReader(ReaderDto readerDto);
}
