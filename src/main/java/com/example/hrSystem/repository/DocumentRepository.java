package com.example.hrSystem.repository;

import com.example.hrSystem.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document ,Integer>
{
    @Query(value = " SELECT d from Document d JOIN FETCH d.documentType WHERE d.id = :id")
    Optional<Document> findFullDocumentById(@Param("id") Integer id);


}
