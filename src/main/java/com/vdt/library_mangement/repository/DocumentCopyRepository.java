package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.DocumentCopy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentCopyRepository extends JpaRepository<DocumentCopy, String> {
    Optional<DocumentCopy> findByDocumentCopyCode(String documentCopyCode);
}
