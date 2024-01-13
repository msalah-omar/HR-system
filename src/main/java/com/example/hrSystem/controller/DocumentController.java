package com.example.hrSystem.controller;


import com.example.hrSystem.Dto.DocumentDto;
import com.example.hrSystem.handler.DocumentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
@AllArgsConstructor
@Tag(name = "Document", description = "REST API for Document")
public class DocumentController {
    private DocumentHandler documentHandler;

    @GetMapping("/{id}")
    @Operation(summary = "Get document By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return documentHandler.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update A Document")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody DocumentDto dto) {
        return documentHandler.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete A document")
    public ResponseEntity delete(@PathVariable Integer id) {
        return documentHandler.delete(id);
    }

//    @PostMapping("/{id}/upload")
//    @Operation(summary = "upload A Document")
//    public ResponseEntity update(@PathVariable Integer id, @RequestBody MultipartFile file) {
//        return documentHandler.upload(id, file);
   // }

    @PostMapping("/upload/{documentTypeId}/document-type")
    @Operation(summary = "upload A Document")
    public ResponseEntity<?> handleFileUpload(MultipartFile file,@PathVariable(value = "documentTypeId") Integer documentTypeId) throws IOException
    {
        return  documentHandler.FileUpload(file ,documentTypeId);
    }



}
