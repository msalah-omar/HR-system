package com.example.hrSystem.handler;



import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.EmployeesDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.*;
import com.example.hrSystem.entity.Department;
import com.example.hrSystem.entity.Employees;
import com.example.hrSystem.exception.*;
import com.example.hrSystem.mapper.EmployeesMapper;
import com.example.hrSystem.mapper.PaginationMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeesHandler
{
    private EmployeesService employeesService;
    private ProjectService projectService;
    private EmployeesMapper mapper;
    private DepartmentService departmentService;
    private BranchesService branchesService;
    private PaginationMapper paginationMapper;
    private TimeSheetDetailService timeSheetDetailService;

    public ResponseEntity<?> save (EmployeesDto dto)
    {

        Employees employees = mapper.toEntity(dto);
        employees.setBranches(branchesService.getById(dto.getBranches().getId()).get());
        employees.setDepartment(departmentService.getById(dto.getDepartment().getId()).get());
        employees.setProject(projectService.getById(dto.getProject().getId()).get());
        employeesService.save(employees);
        EmployeesDto employeesDto = mapper.toDto(employees);
        return ResponseEntity.created(URI.create("/employees/" + employees.getId())).body(employeesDto);
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

        return ResponseEntity.ok(mapper.toDto(employees));
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
        return ResponseEntity.noContent().build();
    }
}
