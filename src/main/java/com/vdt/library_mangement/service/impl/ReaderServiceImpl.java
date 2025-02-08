package com.vdt.library_mangement.service.impl;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.Role;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.response.ReaderResponse;
import com.vdt.library_mangement.service.ReaderService;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    private final UserRepository userRepository;

    @Override
    public ReaderResponse createReader(ReaderDto readerDto) {
        Optional<User> existingUser = userRepository.findByEmail(readerDto.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // Reader reader = ReaderMapper.toEntity(readerDto);

        User user = User.builder()
            .fullName(readerDto.getFullName())
            .dob(readerDto.getDob())
            .gender(User.Gender.valueOf(readerDto.getGender().toUpperCase()))
            .phoneNumber(readerDto.getPhoneNumber())
            .address(readerDto.getAddress())
            .identificationNumber(readerDto.getIdentificationNumber())
            .email(readerDto.getEmail())
            .password(readerDto.getPassword())
            .isActive(false)
            .build();

        Role role = Role.builder()
            .id(2L)
            .build();
        
        user.setRole(role);
        user.setActive(false);
        
        Reader.ReaderBuilder readerBuilder = Reader.builder()
            .user(user);

        if (readerDto.getStudentId() != null) {
            readerBuilder.studentId(readerDto.getStudentId());
        }

        Reader reader = readerBuilder.build();

        Reader savedReader = readerRepository.save(reader);

        ReaderResponse savedReaderResponse = new ReaderResponse();
        savedReaderResponse.setFullName(savedReader.getUser().getFullName());
        savedReaderResponse.setDob(savedReader.getUser().getDob());
        savedReaderResponse.setGender(savedReader.getUser().getGender().toString());
        savedReaderResponse.setPhoneNumber(savedReader.getUser().getPhoneNumber());
        savedReaderResponse.setAddress(savedReader.getUser().getAddress());
        savedReaderResponse.setIdentificationNumber(savedReader.getUser().getIdentificationNumber());
        savedReaderResponse.setEmail(savedReader.getUser().getEmail());
        savedReaderResponse.setStudentId(savedReader.getStudentId());
    
        return savedReaderResponse;
    }
}