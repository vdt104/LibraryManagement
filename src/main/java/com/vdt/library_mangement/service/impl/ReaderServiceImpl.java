package com.vdt.library_mangement.service.impl;

import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.DocumentCopy;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.ReaderCard;
import com.vdt.library_mangement.entity.Role;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.exception.EmailAlreadyExistsException;
import com.vdt.library_mangement.exception.ResourceNotFoundException;
import com.vdt.library_mangement.mapper.ReaderMapper;
import com.vdt.library_mangement.repository.DocumentCopyRepository;
import com.vdt.library_mangement.repository.ReaderCardRepository;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.response.UserResponse;
import com.vdt.library_mangement.service.ReaderService;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    private final UserRepository userRepository;

    private final ReaderCardRepository readerCardRepository;

    private final DocumentCopyRepository documentCopyRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ReaderDto getReaderById(String userId) {
        Reader reader = readerRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Reader", "id", userId));

        return ReaderMapper.toDTO(reader);
    }

    @Override
    public List<ReaderDto> getAllReaders() {
        List<Reader> readers = readerRepository.findAll();

        List<ReaderDto> readerDtos = new ArrayList<>();

        for (Reader reader : readers) {
            readerDtos.add(ReaderMapper.toDTO(reader));
        }

        return readerDtos;        
    }

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
    public UserResponse activateReader(String userId) {
        Reader existingReader = readerRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Reader", "id", userId));

        User user = existingReader.getUser();
        user.setActive(true);

        // Cập nhật ReaderCard
        ReaderCard readerCard = existingReader.getReaderCard();

        String pin = null;
        String password = null;

        if (readerCard.getCreatedAt().equals(readerCard.getUpdatedAt())) {
            // Tạo mã PIN 6 số ngẫu nhiên
            pin = generateRandomPin();
            System.out.println("Generated PIN: " + pin);
            String encodedPin = passwordEncoder.encode(pin);

            password = generateRandomPassword();
            System.out.println("Generated password: " + password);
            String encodedPassword = passwordEncoder.encode(password);

            user.setPassword(encodedPassword);

            readerCard.setPin(encodedPin);
            readerCard.setIssueDate(new Date());
        }
        readerCard.setIssueDate(new Date());
        readerCard.setStatus(ReaderCard.Status.ACTIVE);

        existingReader.setReaderCard(readerCard);

        existingReader.setUser(user);

        readerRepository.save(existingReader);

        return UserResponse.builder()
            .password(password)
            .pin(pin)
            .build();
    }

    @Override
    public ReaderDto deactivateReader(String userId) {
        Reader existingReader = readerRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Reader", "id", userId));

        ReaderCard readerCard = existingReader.getReaderCard();

        User user = existingReader.getUser();
        user.setActive(false);
        
        readerCard.setStatus(ReaderCard.Status.INACTIVE);
        existingReader.setReaderCard(readerCard);

        existingReader.setUser(user);

        Reader updatedReader = readerRepository.save(existingReader);

        return ReaderMapper.toDTO(updatedReader);
    }

    @Override
    public void addDocumentToBookshelf(String userId, String documentCopyCode) throws Exception {
        Reader existingReader = readerRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Reader", "id", userId));

        DocumentCopy documentCopy = documentCopyRepository.findByDocumentCopyCode(documentCopyCode)
            .orElseThrow(() -> new ResourceNotFoundException("Document copy", "code", documentCopyCode));

        if (documentCopy.getStatus() != DocumentCopy.Status.AVAILABLE) {
            throw new Exception("Document copy is not available");
        }

        existingReader.getDocumentCopies().add(documentCopy);

        readerRepository.save(existingReader);
    }

    private String generateRandomPin() {
        SecureRandom random = new SecureRandom();
        int pin = 100000 + random.nextInt(900000);
        return String.valueOf(pin);
    }

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";

        String allCharacters = upperCaseLetters + lowerCaseLetters + numbers + specialCharacters;
        StringBuilder password = new StringBuilder();

        // Đảm bảo có ít nhất 1 ký tự viết hoa
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        // Đảm bảo có ít nhất 1 ký tự viết thường
        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        // Đảm bảo có ít nhất 1 số
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        // Đảm bảo có ít nhất 1 ký tự đặc biệt
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        // Thêm các ký tự ngẫu nhiên còn lại để đạt độ dài tối thiểu là 6
        for (int i = 4; i < 6; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // Trộn các ký tự để tạo mật khẩu ngẫu nhiên
        return password.toString();
    }
}