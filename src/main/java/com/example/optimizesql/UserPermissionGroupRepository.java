package com.example.optimizesql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPermissionGroupRepository extends JpaRepository<UserPermissionGroup, UUID> {
}
