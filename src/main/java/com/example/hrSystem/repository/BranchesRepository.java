package com.example.hrSystem.repository;

import com.example.hrSystem.entity.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchesRepository extends JpaRepository<Branches , Integer >
{
}
