package com.example.hrSystem.repository;

import com.example.hrSystem.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT r FROM Role r where r.code=:code")
    Optional<Role> findByCode(@Param("code") String code);

}
