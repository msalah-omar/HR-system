package com.example.hrSystem.controller;

import com.example.hrSystem.Dto.BranchesDto;
import com.example.hrSystem.handler.BranchesHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Tag(name = "Branches")
@RequestMapping("/branches")
@RestController
@Component
@AllArgsConstructor
public class BranchesController
{
    private BranchesHandler branchesHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all branches")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return branchesHandler.getAll( page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get branches by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return branchesHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new branches")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody BranchesDto branchesDto)
    {
        return branchesHandler.save(branchesDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update branches")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody BranchesDto dto , @PathVariable Integer id)
    {
        return branchesHandler.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete branches By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return branchesHandler.delete(id);
    }


}
