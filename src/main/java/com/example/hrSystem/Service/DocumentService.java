package com.example.hrSystem.Service;



import com.example.hrSystem.entity.Document;
import com.example.hrSystem.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentService {


    private DocumentRepository documentRepository;

    public Page<Document> getAll(Integer page, Integer size) {
        return documentRepository.findAll(PageRequest.of(page, size));
    }

    public Document getReferenceById(Integer id) {
        return documentRepository.getById(id);
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

    public Optional<Document> getById(Integer id) {
        return documentRepository.findById(id);
    }

}