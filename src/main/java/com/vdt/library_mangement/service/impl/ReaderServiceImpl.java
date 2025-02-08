package com.vdt.library_mangement.service.impl;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.Role;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.exception.ResourceNotFoundException;
import com.vdt.library_mangement.mapper.ReaderMapper;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.ReaderService;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    private final UserRepository userRepository;

    @Override
    public ReaderDto createReader(ReaderDto readerDto) {
        Optional<User> existingUser = userRepository.findByEmail(readerDto.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Reader reader = ReaderMapper.toEntity(readerDto);

        User user = reader.getUser();
        user.setActive(false);
        Role role = Role.builder()
            .id(2L)
            .build();
        user.setRole(role);

        reader.setUser(user);

        Reader savedReader = readerRepository.save(reader);

        return ReaderMapper.toDTO(savedReader);
    }

    @Override
    public ReaderDto updateReader(String userId, ReaderDto readerDto) {
        Reader existingReader = readerRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Reader", "id", userId));

        User user = existingReader.getUser();

        // Kiểm tra xem email mới có trùng với email hiện tại hay không
        if (!user.getEmail().equals(readerDto.getEmail())) {
            // Kiểm tra xem email mới có tồn tại trong cơ sở dữ liệu hay không
            Optional<User> existingUser = userRepository.findByEmail(readerDto.getEmail());
            if (existingUser.isPresent()) {
                throw new EmailAlreadyExistsException("Email already exists");
            }
        }
        
        user.setFullName(readerDto.getFullName());
        user.setDob(readerDto.getDob());
        user.setGender(User.Gender.valueOf(readerDto.getGender())); 
        user.setPhoneNumber(readerDto.getPhoneNumber());
        user.setAddress(readerDto.getAddress());
        user.setIdentificationNumber(readerDto.getIdentificationNumber());
        user.setEmail(readerDto.getEmail());

        existingReader.setUser(user);

        Reader updatedReader = readerRepository.save(existingReader);

        return ReaderMapper.toDTO(updatedReader);
    }
}