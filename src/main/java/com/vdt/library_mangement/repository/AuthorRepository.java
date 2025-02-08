package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.Author;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
    Optional<Author> findByName(String name);
}
