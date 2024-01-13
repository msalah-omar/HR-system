package com.example.hrSystem.controller;


import com.example.hrSystem.handler.RoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
@Tag(name = "role", description = "REST API for role")
@Log4j2
public class RoleController {
    private RoleHandler roleHandler;

    @GetMapping
    @Operation(summary = "get All roles ")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return roleHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return roleHandler.getById(id);
    }

}
