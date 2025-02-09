package com.vdt.library_mangement.service;

import com.vdt.library_mangement.dto.LibrarianDto;
import com.vdt.library_mangement.response.UserResponse;

public interface LibrarianService {
    UserResponse createLibrarian(LibrarianDto librarianDto);
}
