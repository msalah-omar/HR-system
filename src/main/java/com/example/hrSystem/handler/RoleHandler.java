package com.example.hrSystem.handler;

import com.example.hrSystem.Dto.RoleDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.RoleService;
import com.example.hrSystem.entity.Role;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.mapper.PaginationMapper;
import com.example.hrSystem.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleHandler {
    private RoleService roleService;
    private RoleMapper roleMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(RoleDto dto)
    {
        Role role= roleMapper.toEntity(dto);
        role= roleService.save(role);
        dto=roleMapper.toDto(role);
        return ResponseEntity.created(URI.create("/role/" + role.getId())).body(dto);
    }

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Role> rolePage = roleService.getAll(page, size);
        List<RoleDto> roleDtoList = roleMapper.toDto(rolePage.getContent());
        PaginatedResultDto<RoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(roleDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(rolePage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        Role role = roleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        RoleDto roleDto = roleMapper.toDto(role);
        return ResponseEntity.ok(roleDto);
    }
}
