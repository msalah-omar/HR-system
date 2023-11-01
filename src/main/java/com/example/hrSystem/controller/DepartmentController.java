package com.example.hrSystem.controller;


import com.example.hrSystem.Dto.BranchesDto;
import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.handler.DepartmentHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Department")
@RequestMapping("/department")
@RestController
@Component
@AllArgsConstructor
public class DepartmentController
{
    private DepartmentHandler departmentHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all department")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return departmentHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get department by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return departmentHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new department")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody DepartmentDto departmentDto)
    {
        return departmentHandler.save(departmentDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update department")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody DepartmentDto departmentDto , @PathVariable Integer id)
    {
        return departmentHandler.update(id,departmentDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete department By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return departmentHandler.delete(id);
    }

}
