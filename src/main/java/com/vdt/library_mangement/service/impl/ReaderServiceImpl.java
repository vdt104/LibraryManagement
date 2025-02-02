package com.vdt.library_mangement.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.mapper.ReaderMapper;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.service.ReaderService;

import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    @Override
    @Transactional
    public ReaderDto createReader(ReaderDto readerDto) {
        Reader reader = ReaderMapper.toEntity(readerDto);

        // Ensure the user is set correctly
        reader.setUser(reader.getUser());

        Reader savedReader = readerRepository.save(reader);

        return ReaderMapper.toDTO(savedReader);
    }
}