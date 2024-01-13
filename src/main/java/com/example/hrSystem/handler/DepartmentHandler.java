package com.example.hrSystem.handler;



import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.DepartmentService;
import com.example.hrSystem.entity.Department;
import com.example.hrSystem.exception.ErrorCodes;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.exception.ResourceRelatedException;
import com.example.hrSystem.exception.Response;
import com.example.hrSystem.mapper.PaginationMapper;
import com.example.hrSystem.mapper.DepartmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class DepartmentHandler
{

    private DepartmentService departmentService;
    private DepartmentMapper mapper;
    private PaginationMapper paginationMapper;



    public ResponseEntity<?> save(DepartmentDto dto) {
        Department department = mapper.toEntity(dto);
        departmentService.save(department);
        DepartmentDto departmentDto = mapper.toDto(department);
        return ResponseEntity.created(URI.create("/department/" + department.getId())).body(departmentDto);
    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        Page<Department> departments = departmentService.getAll(page, size);
        List<DepartmentDto> dtos = mapper.toDto(departments.getContent());
        PaginatedResultDto<DepartmentDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(departments));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)

    {
        Department department = departmentService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Department.class.getSimpleName(),id));

        DepartmentDto departmentDto = mapper.toDto(department);
        return ResponseEntity.ok(departmentDto);

    }

    public ResponseEntity<?> update(Integer id, DepartmentDto departmentDto)
    {

        Department department=departmentService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Department.class.getSimpleName(),id));
        mapper.updateEntityFromDto(departmentDto, department);
        departmentService.update(department);
        DepartmentDto dto = mapper.toDto(department);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
        Department department = departmentService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Department.class.getSimpleName(), id));
        try {
            departmentService.delete(department);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Department.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }
}
