package com.vdt.library_mangement.service.impl;

import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.LibrarianDto;
import com.vdt.library_mangement.entity.Librarian;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.mapper.LibrarianMapper;
import com.vdt.library_mangement.repository.LibrarianRepository;
import com.vdt.library_mangement.service.LibrarianService;

@Service
@AllArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

    @Override
    public LibrarianDto createLibrarian(LibrarianDto librarianDto) {
        Librarian librarian = LibrarianMapper.toEntity(librarianDto);

        User user = librarian.getUser();
        user.setId(UUID.randomUUID().toString());

        // Kiểm tra nếu mật khẩu chưa được đặt, sử dụng mật khẩu mặc định
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode("Admin@2025"));
        }

        user.setRole(User.Role.LIBRARIAN);
        user.setStatus(User.Status.ACTIVE);
        librarian.setUser(user);

        Librarian savedLibrarian = librarianRepository.save(librarian);

        return LibrarianMapper.toDTO(savedLibrarian);
    }
}