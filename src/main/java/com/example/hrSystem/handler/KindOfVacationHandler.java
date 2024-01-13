package com.example.hrSystem.handler;



import com.example.hrSystem.Dto.KindOfVacationDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;

import com.example.hrSystem.Service.KindOfVacationService;

import com.example.hrSystem.entity.KindOfVacation;
import com.example.hrSystem.exception.ErrorCodes;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.exception.ResourceRelatedException;
import com.example.hrSystem.exception.Response;
import com.example.hrSystem.mapper.KindOfVacationMapper;
import com.example.hrSystem.mapper.PaginationMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class KindOfVacationHandler
{
    private KindOfVacationService kindOfVacationService;
    private KindOfVacationMapper mapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(KindOfVacationDto dto )
    {


        KindOfVacation kindOfVacation = mapper.toEntity(dto);
        kindOfVacationService.save(kindOfVacation);
        return ResponseEntity.ok(mapper.toDto(kindOfVacation));

    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        Page<KindOfVacation> kindOfVacations = kindOfVacationService.getAll(page, size);
        List<KindOfVacationDto> dtos = mapper.toDto(kindOfVacations.getContent());
        PaginatedResultDto<KindOfVacationDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(kindOfVacations));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)

    {
        KindOfVacation kindOfVacation = kindOfVacationService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(KindOfVacation.class.getSimpleName(),id));

        KindOfVacationDto kindOfVacationDto = mapper.toDto(kindOfVacation);
        return ResponseEntity.ok(kindOfVacationDto);

    }

    public ResponseEntity<?> update(Integer id, KindOfVacationDto kindOfVacationDto)
    {

        KindOfVacation kindOfVacation=kindOfVacationService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(KindOfVacation.class.getSimpleName(),id));
        mapper.updateEntityFromDto(kindOfVacationDto, kindOfVacation);
        kindOfVacationService.update(kindOfVacation);
        KindOfVacationDto dto = mapper.toDto(kindOfVacation);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        KindOfVacation kindOfVacation = kindOfVacationService.getById(id).orElseThrow(() -> new ResourceNotFoundException(KindOfVacation.class.getSimpleName(), id));
        try
        {
            kindOfVacationService.delete(kindOfVacation);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(KindOfVacation.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
