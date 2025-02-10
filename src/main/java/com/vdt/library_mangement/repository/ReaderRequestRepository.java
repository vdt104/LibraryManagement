package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.ReaderRequest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRequestRepository extends JpaRepository<ReaderRequest, String> {
    Optional<ReaderRequest> findById(String id);
}
