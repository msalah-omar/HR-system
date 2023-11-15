package com.example.hrSystem.Service;

import com.example.hrSystem.entity.Department;
import com.example.hrSystem.entity.DocumentType;
import com.example.hrSystem.repository.DepartmentRepository;
import com.example.hrSystem.repository.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentTypeService
{
    private DocumentTypeRepository documentTypeRepository;


    public Page<DocumentType> getAll(Integer page, Integer size)
    {
        return documentTypeRepository.findAll(PageRequest.of(page, size));
    }

    public DocumentType save(DocumentType documentType)
    {
        return documentTypeRepository.save(documentType);
    }

    public Optional<DocumentType> getById(Integer id)
    {
        return documentTypeRepository.findById(id);
    }

    public DocumentType getReferenceById(Integer id) {
        return documentTypeRepository.getById(id);
    }

    public DocumentType update(DocumentType documentType)
    {
        return documentTypeRepository.save(documentType);
    }


    public List<DocumentType> delete(DocumentType documentType)
    {
        documentTypeRepository.delete(documentType);
        return null;
    }
}
