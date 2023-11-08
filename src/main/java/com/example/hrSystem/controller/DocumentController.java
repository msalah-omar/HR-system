package com.example.hrSystem.controller;


import com.example.hrSystem.handler.DocumentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/document")
@AllArgsConstructor
@Tag(name = "document", description = "REST API for document")
public class DocumentController {

    private DocumentHandler documentHandler;

    @GetMapping("/{id}")
    @Operation(summary = "Get Document By Id")
    public ResponseEntity<?> downloadById(@PathVariable(value = "id") Integer id , HttpServletResponse response) {
        return documentHandler.downloadDocument(id,response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete A party")
    public ResponseEntity delete(@PathVariable Integer id) {
        return documentHandler.delete(id);
    }
    @PostMapping
    @Operation(summary = "Add New Document")
    public ResponseEntity save (@RequestParam("file")MultipartFile file ) {
        return documentHandler.uploadDocument(file);
    }
}
