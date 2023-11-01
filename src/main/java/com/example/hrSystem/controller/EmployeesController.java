package com.example.hrSystem.controller;

import com.example.hrSystem.Dto.BranchesDto;
import com.example.hrSystem.Dto.EmployeesDto;
import com.example.hrSystem.handler.EmployeesHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Employees")
@RequestMapping("/employees")
@RestController
@Component
@AllArgsConstructor

public class EmployeesController
{
    private EmployeesHandler employeesHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all employees")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return employeesHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get employees by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return employeesHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new employees")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody EmployeesDto employeesDto)
    {
        return employeesHandler.save(employeesDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update employees")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody EmployeesDto dto , @PathVariable Integer id)
    {
        return employeesHandler.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete employees By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return employeesHandler.delete(id);
    }
}
