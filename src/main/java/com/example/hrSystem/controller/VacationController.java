package com.example.hrSystem.controller;


import com.example.hrSystem.Dto.VacationDto;
import com.example.hrSystem.handler.VacationHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Vacation")
@RequestMapping("/vacation")
@RestController
@Component
@AllArgsConstructor
public class VacationController
{
    private VacationHandler vacationHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all vacation")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return vacationHandler.getAll( page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get vacation by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return vacationHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new vacation")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody VacationDto vacationDto)
    {
        return vacationHandler.save(vacationDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update vacation")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody VacationDto dto , @PathVariable Integer id)
    {
        return vacationHandler.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete vacation By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return vacationHandler.delete(id);
    }

}
