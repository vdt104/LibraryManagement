package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.ReaderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRequestRepository extends JpaRepository<ReaderRequest, String> {
    
}
