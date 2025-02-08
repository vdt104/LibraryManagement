package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.Document;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {
    Optional<Document> findByDocumentCode(String documentCode);
}
