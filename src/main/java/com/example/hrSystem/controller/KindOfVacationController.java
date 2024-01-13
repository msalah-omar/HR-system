package com.example.hrSystem.controller;

import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.KindOfVacationDto;
import com.example.hrSystem.handler.KindOfVacationHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "kind of vacation")
@RequestMapping("/kind of vacation")
@RestController
@Component
@AllArgsConstructor
public class KindOfVacationController
{
    private KindOfVacationHandler kindOfVacationHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all kind of vacation")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return kindOfVacationHandler.getAll( page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get kind of vacation by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return kindOfVacationHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new kind of vacation")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody KindOfVacationDto kindOfVacationDto)
    {
        return kindOfVacationHandler.save(kindOfVacationDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update kind of vacation")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody KindOfVacationDto kindOfVacationDto , @PathVariable Integer id)
    {
        return kindOfVacationHandler.update(id,kindOfVacationDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete kind of vacation By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return kindOfVacationHandler.delete(id);
    }

}
