package com.example.hrSystem.controller;


import com.example.hrSystem.Dto.ProjectDto;
import com.example.hrSystem.handler.ProjectHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Project")
@RequestMapping("/project")
@RestController
@Component
@AllArgsConstructor
public class ProjectController
{
    private ProjectHandler projectHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all project")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return projectHandler.getAll( page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get project by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return projectHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new project")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody ProjectDto projectDto)
    {
        return projectHandler.save(projectDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update project")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody ProjectDto dto , @PathVariable Integer id)
    {
        return projectHandler.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete project By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return projectHandler.delete(id);
    }

}
