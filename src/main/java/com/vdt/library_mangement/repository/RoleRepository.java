package com.vdt.library_mangement.repository;

import com.vdt.library_mangement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
