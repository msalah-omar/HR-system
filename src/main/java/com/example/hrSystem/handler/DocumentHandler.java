package com.example.hrSystem.handler;

import com.example.hrSystem.Dto.DocumentDto;
import com.example.hrSystem.Service.DocumentService;
import com.example.hrSystem.entity.Document;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.mapper.DocumentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Component
@AllArgsConstructor
public class DocumentHandler {

    private DocumentService documentService;
    private DocumentMapper documentMapper;


   public ResponseEntity uploadDocument(MultipartFile file)  {
       Document document = new Document();
       document.setDocumentName(file.getOriginalFilename());
       document.setDocumentType(null);
       document.setContentType(file.getContentType());
       document.setSize(file.getSize());

       try {
           document.setContent(file.getBytes());
      } catch (IOException e) {
           throw new RuntimeException(e);
       }

       documentService.save(document);
       return ResponseEntity.created(URI.create("/document/" + document.getId()))
               .body(documentMapper.toDto(document));

   }
    public ResponseEntity<?> downloadDocument(Integer id, HttpServletResponse response){
        Document document = documentService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Document.class.getSimpleName(), id));
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + document.getDocumentName();

        response.setHeader(headerKey,headerValue);

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(document.getContent());
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(documentMapper.toDto(document));
    }
    public ResponseEntity<?> getById(Integer id) {
        Document document = documentService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Document.class.getSimpleName(), id));
        DocumentDto documentDto = documentMapper.toDto(document);
        return ResponseEntity.ok(documentDto);
    }

    public ResponseEntity<?> delete(Integer id) {
        Document document = documentService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Document.class.getSimpleName(), id));
        documentService.delete(document);
        return ResponseEntity.noContent().build();
    }
}
