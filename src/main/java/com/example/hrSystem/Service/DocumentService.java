package com.example.hrSystem.Service;


import com.example.hrSystem.entity.Document;
import com.example.hrSystem.repository.DocumentRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DocumentService {

    private DocumentRepository documentRepository;


    public Optional<Document> getById(Integer id) {
        return documentRepository.findById(id);
    }

    public Document getReferenceById(Integer id) {
        return documentRepository.getById(id);
    }

    public Optional<Document> getFullDocumentById(Integer id) {
        return documentRepository.findFullDocumentById(id);
    }

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public void update(Document document) {
        documentRepository.save(document);
    }

    public void delete(Document document) {
        documentRepository.delete(document);
    }

    public void upload(Integer documentId, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get (documentId + "_" + file.getOriginalFilename());
            Files.write(path, bytes);
            Document document = documentRepository.findById(documentId).get();
            document.setName(documentId + "_" + file.getOriginalFilename());
            document.setMediaType(file.getContentType());
            documentRepository.save(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
