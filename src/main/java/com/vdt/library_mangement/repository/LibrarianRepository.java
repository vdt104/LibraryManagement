package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, String> {
}