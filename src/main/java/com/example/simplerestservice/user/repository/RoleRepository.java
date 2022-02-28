package com.example.simplerestservice.user.repository;

import com.example.simplerestservice.user.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
