package com.example.hrSystem.controller;

import com.example.hrSystem.Dto.TimeSheetDetailDto;
import com.example.hrSystem.handler.TimeSheetDetailHandler;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Tag(name = "Time Sheet detail")
@RequestMapping("/time sheet detail")
@RestController
@Component
@AllArgsConstructor
public class TimeSheetDetailController
{
    private TimeSheetDetailHandler timeSheetDetailHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all time sheet detail")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return timeSheetDetailHandler.getAll( page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get time sheet detail by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return timeSheetDetailHandler.getById(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new time sheet detail")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody TimeSheetDetailDto timeSheetDetailDto )
    {
        return timeSheetDetailHandler.save(timeSheetDetailDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update time sheet detail")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody TimeSheetDetailDto dto , @PathVariable Integer id)
    {
        return timeSheetDetailHandler.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete time sheet detail By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return timeSheetDetailHandler.delete(id);
    }

}
