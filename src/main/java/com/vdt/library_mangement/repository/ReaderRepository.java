package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}