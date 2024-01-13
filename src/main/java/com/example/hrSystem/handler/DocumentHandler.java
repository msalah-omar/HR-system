package com.example.hrSystem.handler;

import com.example.hrSystem.Dto.DocumentDto;
import com.example.hrSystem.Service.DocumentService;
import com.example.hrSystem.Service.DocumentTypeService;
import com.example.hrSystem.entity.Document;
import com.example.hrSystem.entity.DocumentType;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.mapper.DocumentMapper;
import com.example.hrSystem.mapper.PaginationMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;


@Component
@AllArgsConstructor
public class DocumentHandler
{
    private DocumentTypeService documentTypeService;
    private DocumentService documentService;
    private DocumentMapper documentMapper;
    private PaginationMapper paginationMapper;


    @Autowired
    public DocumentHandler(DocumentService documentService, DocumentMapper documentMapper, PaginationMapper paginationMapper)
    {

        this.documentService = documentService;
        this.documentMapper = documentMapper;
        this.paginationMapper = paginationMapper;

    }


    public ResponseEntity<?> getById(Integer id)
    {
        Document document = documentService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Document.class.getSimpleName(), id));
        DocumentDto documentDto = documentMapper.toDto(document);
        documentDto.setPath("C:/Users/Public/Downloads");
        return ResponseEntity.ok(documentDto);
    }

    public ResponseEntity<?> save(Integer documentId, DocumentDto dto)
    {
        DocumentType documentType = documentTypeService.getById(documentId).orElseThrow(() -> new ResourceNotFoundException(DocumentType.class.getSimpleName(), documentId));

        Document document = documentMapper.toEntity(dto);
        document.setDocumentType(documentType);
        documentService.save(document);
        DocumentDto documentDto = documentMapper.toDto(document);
        return ResponseEntity.created(URI.create("/document/" + document.getId())).body(documentDto);
    }

    public ResponseEntity<?> delete(Integer id)
    {
        Document document = documentService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Document.class.getSimpleName(), id));
        try
        {
            documentService.delete(document);
        } catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
        }
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<?> update(Integer id, DocumentDto documentDto)
    {
        Document document = documentService.getFullDocumentById(id).orElseThrow(() -> new ResourceNotFoundException(Document.class.getSimpleName(), id));
        Document dto = documentMapper.updateEntityFromDto(documentDto, document);
        documentService.update(dto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<DocumentDto> upload(Integer documentId, MultipartFile file)
    {
        documentService.getById(documentId).orElseThrow(() -> new ResourceNotFoundException(Document.class.getSimpleName(), documentId));
        documentService.upload(documentId, file);
        return ResponseEntity.created(URI.create("/document/" + documentId)).body(null);
    }

    public ResponseEntity<?> FileUpload(MultipartFile file, Integer documentTypeId) throws IOException
    {
        String fileName = file.getOriginalFilename();

        file.transferTo(new File("C:\\Users\\Public\\Downloads\\" + fileName));
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();
        Document document = new Document();
        document.setName(fileName);
        document.setPath(downloadUrl);
        document.setMediaType(file.getContentType());
     //   document.setDocumentType(documentTypeService.getById(documentTypeId).get());
        documentService.save(document);
        return ResponseEntity.ok(documentMapper.toDto(document));
    }

}
