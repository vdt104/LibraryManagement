package com.vdt.library_mangement.service.impl;

import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.mapper.ReaderMapper;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.service.ReaderService;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    @Override
    public ReaderDto createReader(ReaderDto readerDto) {
        Reader reader = ReaderMapper.toEntity(readerDto);

        User user = reader.getUser();
        user.setId(UUID.randomUUID().toString());
        user.setRole(User.Role.READER);
        user.setStatus(User.Status.INACTIVE);

        reader.setUser(user);

        Reader savedReader = readerRepository.save(reader);
        
        return ReaderMapper.toDTO(savedReader);
    }
}