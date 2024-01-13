package com.example.hrSystem.handler;


import com.example.hrSystem.Dto.VacationDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;

import com.example.hrSystem.Service.VacationService;
import com.example.hrSystem.entity.Vacation;
import com.example.hrSystem.exception.ErrorCodes;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.exception.ResourceRelatedException;
import com.example.hrSystem.exception.Response;

import com.example.hrSystem.mapper.PaginationMapper;
import com.example.hrSystem.mapper.VacationMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VacationHandler
{
    private VacationService vacationService;
    private VacationMapper mapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(VacationDto dto)
    {
        Vacation vacation = mapper.toEntity(dto);
        vacationService.save(vacation);
        return ResponseEntity.ok(mapper.toDto(vacation));
    }

    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        Page<Vacation> branches = vacationService.getAll(page, size);
        List<VacationDto> dtos = mapper.toDto(branches.getContent());
        PaginatedResultDto<VacationDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(branches));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)
    {
        Vacation vacation = vacationService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Vacation.class.getSimpleName(), id));
        VacationDto vacationDto = mapper.toDto(vacation);
        return ResponseEntity.ok(vacationDto);
    }

    public ResponseEntity<?> update(Integer id, VacationDto vacationDto)
    {

        Vacation vacation = vacationService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Vacation.class.getSimpleName(), id));
        mapper.updateEntityFromDto(vacationDto, vacation);
        vacationService.update(vacation);
        VacationDto dto = mapper.toDto(vacation);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)

    {
        Vacation vacation = vacationService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Vacation.class.getSimpleName(), id));
        try
        {
            vacationService.delete(vacation);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(Vacation.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
