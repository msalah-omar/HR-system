package com.example.hrSystem.handler;


import com.example.hrSystem.Dto.TimeSheetDetailDto;

import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.EmployeesService;
import com.example.hrSystem.Service.TimeSheetDetailService;

import com.example.hrSystem.entity.TimeSheetDetail;
import com.example.hrSystem.exception.ErrorCodes;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.exception.ResourceRelatedException;

import com.example.hrSystem.mapper.PaginationMapper;
import com.example.hrSystem.mapper.TimeSheetDetailMapper;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.net.URI;
import java.time.Duration;
import java.util.List;


@Component
@AllArgsConstructor
public class TimeSheetDetailHandler
{
    private TimeSheetDetailService timeSheetDetailService;
    private TimeSheetDetailMapper mapper;
    private PaginationMapper paginationMapper;
    private EmployeesService employeesService;

    public ResponseEntity<?> save(TimeSheetDetailDto dto) {
        TimeSheetDetail timeSheetDetail = mapper.toEntity(dto);
        timeSheetDetail.setEmployees(employeesService.getById(dto.getEmployees().getId()).get());
        Duration duration = Duration.between(dto.getStartDate(),dto.getEndDate());
        long l = duration.toHours();
        System.out.println(l);
        l = l - (8);
        System.out.println("after: "+l);
        timeSheetDetail.setOvertimeHours((float) l);
        timeSheetDetailService.save(timeSheetDetail);
        TimeSheetDetailDto timeSheetDetailDto = mapper.toDto(timeSheetDetail);
        return ResponseEntity.created(URI.create("/timeSheetDetail/" + timeSheetDetail.getId())).body(timeSheetDetailDto);
    }


    public ResponseEntity<?> getAll( Integer page , Integer size)
    {
        Page<TimeSheetDetail> timeSheetDetails = timeSheetDetailService.getAll(page, size);
        List<TimeSheetDetailDto> dtos = mapper.toDto(timeSheetDetails.getContent());
        PaginatedResultDto<TimeSheetDetailDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(timeSheetDetails));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)
    {
        TimeSheetDetail timeSheetDetail = timeSheetDetailService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(TimeSheetDetail.class.getSimpleName(),id));
        TimeSheetDetailDto timeSheetDetailDto = mapper.toDto(timeSheetDetail);
        return ResponseEntity.ok(timeSheetDetailDto);
    }

    public ResponseEntity<?> update(Integer id, TimeSheetDetailDto timeSheetDetailDto)
    {

        TimeSheetDetail timeSheetDetail=timeSheetDetailService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(TimeSheetDetail.class.getSimpleName(),id));
        mapper.updateEntityFromDto(timeSheetDetailDto, timeSheetDetail);
        timeSheetDetailService.update(timeSheetDetail);
        TimeSheetDetailDto dto = mapper.toDto(timeSheetDetail);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)

    {
        TimeSheetDetail timeSheetDetail = timeSheetDetailService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(TimeSheetDetail.class.getSimpleName(), id));
        try
        {
            timeSheetDetailService.delete(timeSheetDetail);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(TimeSheetDetail.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }
}
