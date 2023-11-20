package com.example.hrSystem.handler;

import com.example.hrSystem.Dto.BranchesDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.BranchesService;
import com.example.hrSystem.Service.DepartmentService;
import com.example.hrSystem.entity.Branches;
import com.example.hrSystem.exception.ErrorCodes;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.exception.ResourceRelatedException;
import com.example.hrSystem.exception.Response;
import com.example.hrSystem.mapper.BranchesMapper;
import com.example.hrSystem.mapper.PaginationMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BranchesHandler
{

    private BranchesService branchesService;
    private BranchesMapper mapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(BranchesDto dto ,Integer id)
    {
        branchesService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Branches.class.getSimpleName(), id));
        Branches branches = mapper.toEntity(dto);
        branchesService.save(branches);
        return ResponseEntity.ok(mapper.toDto(branches));

    }


    public ResponseEntity<?> getAll(Integer id, Integer page , Integer size)
    {
        branchesService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Branches.class.getSimpleName(), id));
        Page<Branches> branches = branchesService.getAll(page, size);
        List<BranchesDto> dtos = mapper.toDto(branches.getContent());
        PaginatedResultDto<BranchesDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(branches));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)
    {
        Branches branches = branchesService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Branches.class.getSimpleName(),id));
        BranchesDto branchesDto = mapper.toDto(branches);
        return ResponseEntity.ok(branchesDto);
    }

    public ResponseEntity<?> update(Integer id, BranchesDto branchesDto)
    {

        Branches branches=branchesService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Branches.class.getSimpleName(),id));
        mapper.updateEntityFromDto(branchesDto, branches);
        branchesService.update(branches);
        BranchesDto dto = mapper.toDto(branches);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        Branches branches = branchesService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Branches.class.getSimpleName(), id));
        try
        {
            branchesService.delete(branches);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(Branches.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
