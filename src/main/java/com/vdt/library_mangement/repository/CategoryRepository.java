package com.vdt.library_mangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vdt.library_mangement.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
