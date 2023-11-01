package com.example.hrSystem.handler;



import com.example.hrSystem.Dto.EmployeesDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.BranchesService;
import com.example.hrSystem.Service.DepartmentService;
import com.example.hrSystem.Service.EmployeesService;
import com.example.hrSystem.entity.Branches;
import com.example.hrSystem.entity.Employees;
import com.example.hrSystem.exception.*;
import com.example.hrSystem.mapper.EmployeesMapper;
import com.example.hrSystem.mapper.PaginationMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeesHandler
{
    private EmployeesService employeesService;
    private EmployeesMapper mapper;
    private DepartmentService departmentService;
    private BranchesService branchesService;
    private PaginationMapper paginationMapper;


    public ResponseEntity<?> save (EmployeesDto employeesDto)
    {

        Employees employees = mapper.toEntity(employeesDto);
        employees.setBranches(branchesService.getById(employeesDto.getBranches().getId()).get());
        employees.setDepartment(departmentService.getById(employeesDto.getDepartment().getId()).get());
        employeesService.save(employees);
        return ResponseEntity.ok(mapper.toDto(employees));
    }

    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        Page<Employees> employees = employeesService.getAll(page, size);
        List<EmployeesDto> dtos = mapper.toDto(employees.getContent());
        PaginatedResultDto<EmployeesDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(employees));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)
    {
        Employees employees =employeesService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employees.class.getSimpleName(),id));
        EmployeesDto dto = mapper.toDto(employees);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<?> update(Integer id,EmployeesDto employeesDto )
    {
        Employees employees =employeesService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employees.class.getSimpleName(),id));

        Optional<Employees> existedNationalId= employeesService.findNationalId(employeesDto.getNationalId());

        if(existedNationalId.isPresent() && !existedNationalId.get().getId().equals(id)){
            throw new ResourceAlreadyExistsException(Employees.class.getSimpleName(),
                    "NationalId", employeesDto.getNationalId(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        mapper.updateEntityFromDto(employeesDto,employees);
        employees.setId(id);
        employeesService.save(employees);
        EmployeesDto dto= mapper.toDto(employees);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        Employees employees = employeesService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Employees.class.getSimpleName(), id));
        try
        {
            employeesService.delete(employees);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(Employees.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
