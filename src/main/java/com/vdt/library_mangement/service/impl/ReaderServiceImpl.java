package com.vdt.library_mangement.service.impl;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.Role;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.ReaderService;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public ReaderDto createReader(ReaderDto readerDto) {
        Optional<User> existingUser = userRepository.findByEmail(readerDto.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = modelMapper.map(readerDto, User.class);
        // user.setGender(User.Gender.valueOf(readerDto.getGender().toString()));
        user.setActive(false);

        Role role = Role.builder()
            .id(2L)
            .build();
        user.setRole(role);

        Reader reader = Reader.builder()
            .user(user)
            .studentId(readerDto.getStudentId())
            .build();

        Reader savedReader = readerRepository.save(reader);

        ReaderDto savedReaderDto = new ReaderDto();
        savedReaderDto.setFullName(savedReader.getUser().getFullName());
        savedReaderDto.setDob(savedReader.getUser().getDob());
        savedReaderDto.setGender(savedReader.getUser().getGender().toString());
        savedReaderDto.setPhoneNumber(savedReader.getUser().getPhoneNumber());
        savedReaderDto.setAddress(savedReader.getUser().getAddress());
        savedReaderDto.setIdentificationNumber(savedReader.getUser().getIdentificationNumber());
        savedReaderDto.setEmail(savedReader.getUser().getEmail());
        savedReaderDto.setStudentId(savedReader.getStudentId());
    
        return savedReaderDto;
    }
}