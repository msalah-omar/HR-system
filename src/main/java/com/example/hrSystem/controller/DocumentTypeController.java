package com.example.hrSystem.controller;

import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.DocumentTypeDto;
import com.example.hrSystem.handler.DocumentTypeHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Document Type")
@RequestMapping("/document type")
@RestController
@Component
@AllArgsConstructor
public class DocumentTypeController
{
    private DocumentTypeHandler documentTypeHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all Document Type")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                    @RequestParam(value = "id" ,required = false) Integer id)
    {
        return documentTypeHandler.getAll(id,page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get Document Type by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return documentTypeHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new Document Type")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody DocumentTypeDto documentTypeDto)
    {
        return documentTypeHandler.save(documentTypeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update Document Type")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody DocumentTypeDto documentTypeDto , @PathVariable Integer id)
    {
        return documentTypeHandler.update(id,documentTypeDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Document Type By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return documentTypeHandler.delete(id);
    }

}
