package com.example.hrSystem.repository;

import com.example.hrSystem.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository <DocumentType , Integer>
{
}
