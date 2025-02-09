package com.vdt.library_mangement.service.impl;

import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.ReaderCard;
import com.vdt.library_mangement.entity.Role;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.exception.ResourceNotFoundException;
import com.vdt.library_mangement.mapper.ReaderMapper;
import com.vdt.library_mangement.repository.ReaderCardRepository;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.ReaderService;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    private final UserRepository userRepository;

    private final ReaderCardRepository readerCardRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public ReaderDto createReader(ReaderDto readerDto, int expiryPeriod) {
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

        ReaderCard readerCard = ReaderCard.builder()
            .reader(savedReader)
            .expiryPeriod(expiryPeriod)
            .status(ReaderCard.Status.REQUESTED)
            .build();

        readerCardRepository.save(readerCard);

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

    @Override
    public ReaderDto changeReaderStatus(String userId, boolean isActive) {
        Reader existingReader = readerRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Reader", "id", userId));

        User user = existingReader.getUser();
        user.setActive(isActive);

        if (isActive) {

            // Cập nhật ReaderCard
            ReaderCard readerCard = existingReader.getReaderCard();

            if (readerCard.getCreatedAt().equals(readerCard.getUpdatedAt())) {
                // Tạo mã PIN 6 số ngẫu nhiên
                String pin = generateRandomPin();
                System.out.println("Generated PIN: " + pin);
                String encodedPin = passwordEncoder.encode(pin);

                readerCard.setPin(encodedPin);
                readerCard.setIssueDate(new Date());
            }
            readerCard.setIssueDate(new Date());
            readerCard.setStatus(ReaderCard.Status.ACTIVE);

            existingReader.setReaderCard(readerCard);
        } else {
            ReaderCard readerCard = existingReader.getReaderCard();
            
            readerCard.setStatus(ReaderCard.Status.INACTIVE);
            existingReader.setReaderCard(readerCard);
        }

        existingReader.setUser(user);

        Reader updatedReader = readerRepository.save(existingReader);

        return ReaderMapper.toDTO(updatedReader);
    }

    private String generateRandomPin() {
        SecureRandom random = new SecureRandom();
        int pin = 100000 + random.nextInt(900000);
        return String.valueOf(pin);
    }
}