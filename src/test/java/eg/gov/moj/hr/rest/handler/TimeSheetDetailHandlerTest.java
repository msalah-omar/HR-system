package eg.gov.moj.hr.rest.handler;


import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.TimeSheetDetailDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Dto.commen.PaginationDto;
import com.example.hrSystem.Service.TimeSheetDetailService;

import com.example.hrSystem.entity.Department;
import com.example.hrSystem.entity.TimeSheetDetail;
import com.example.hrSystem.handler.TimeSheetDetailHandler;

import com.example.hrSystem.mapper.PaginationMapper;
import com.example.hrSystem.mapper.TimeSheetDetailMapper;
import liquibase.pro.packaged.T;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TimeSheetDetailHandlerTest
{

    private TimeSheetDetailService timeSheetDetailService = mock(TimeSheetDetailService.class);
    private TimeSheetDetailMapper mapper = mock(TimeSheetDetailMapper.class);
    private PaginationMapper paginationMapper = mock(PaginationMapper.class);

    private TimeSheetDetailHandler timeSheetDetailHandler = new TimeSheetDetailHandler(timeSheetDetailService, mapper, paginationMapper);


    @Test
    public void testGetAll()
    {
        List<TimeSheetDetail> timeSheetDetailList = mock(List.class);
        List<TimeSheetDetailDto> timeSheetDetailDtoList = mock(List.class);
        Page<TimeSheetDetail> timeSheetDetailPage = mock(Page.class);
        PaginationDto paginationDto = mock(PaginationDto.class);
        TimeSheetDetail timeSheetDetail = mock(TimeSheetDetail.class);

        Mockito.when(timeSheetDetailPage.getContent()).thenReturn(timeSheetDetailList);
        Mockito.when(paginationMapper.toPaginationDto(timeSheetDetailPage)).thenReturn(paginationDto);
        Mockito.when(timeSheetDetailService.getAll(0, 10)).thenReturn(timeSheetDetailPage);
        Mockito.when(mapper.toDto(timeSheetDetailList)).thenReturn(timeSheetDetailDtoList);

        PaginatedResultDto<TimeSheetDetailDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(timeSheetDetailDtoList);
        paginatedResult.setPagination(paginationDto);

        ResponseEntity<?> response = timeSheetDetailHandler.getAll(0, 10);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().body(paginatedResult);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetById()
    {
        TimeSheetDetail timeSheetDetail = mock(TimeSheetDetail.class);
        TimeSheetDetailDto timeSheetDetailDto = mock(TimeSheetDetailDto.class);

        Mockito.when(timeSheetDetailService.getById(timeSheetDetail.getId())).thenReturn(Optional.of(timeSheetDetail));
        Mockito.when(mapper.toDto(timeSheetDetail)).thenReturn(timeSheetDetailDto);

        ResponseEntity<?> response = timeSheetDetailHandler.getById(timeSheetDetail.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(timeSheetDetailDto);

        assertEquals(expectedResponse, response);
    }

//    @Test
//    public void testSave()
//    {
//        TimeSheetDetail timeSheetDetail = mock(TimeSheetDetail.class);
//        TimeSheetDetailDto timeSheetDetailDto = mock(TimeSheetDetailDto.class);
//
//        Mockito.when(mapper.toEntity(timeSheetDetailDto)).thenReturn(timeSheetDetail);
//        Mockito.when(timeSheetDetailService.save(timeSheetDetail)).thenReturn(timeSheetDetail);
//        Mockito.when(mapper.toDto(timeSheetDetail)).thenReturn(timeSheetDetailDto);
//
//        ResponseEntity<?> response = timeSheetDetailHandler.save(timeSheetDetailDto);
//        ResponseEntity<?> expectedResponse = ResponseEntity.created(URI.create("/timeSheetDetail/" + timeSheetDetail.getId())).body(timeSheetDetailDto);
//
//        assertEquals(expectedResponse, response);
//    }

    @Test
    public void testUpdate()
    {
        TimeSheetDetail timeSheetDetail = mock(TimeSheetDetail.class);
        TimeSheetDetailDto timeSheetDetailDto = mock(TimeSheetDetailDto.class);

        Mockito.when(timeSheetDetailService.getById(timeSheetDetail.getId())).thenReturn(Optional.of(timeSheetDetail));
        Mockito.when(mapper.updateEntityFromDto(timeSheetDetailDto, timeSheetDetail)).thenReturn(timeSheetDetail);

        ResponseEntity<?> response = timeSheetDetailHandler.update(timeSheetDetail.getId(), timeSheetDetailDto);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();

        assertEquals(expectedResponse, response);
    }


    @Test
    void testDelete()
    {
        TimeSheetDetail timeSheetDetail = mock(TimeSheetDetail.class);

        Mockito.when(timeSheetDetailService.getById(timeSheetDetail.getId())).thenReturn(Optional.of(timeSheetDetail));

        ResponseEntity<?> response = timeSheetDetailHandler.delete(timeSheetDetail.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.noContent().build();

        assertEquals(expectedResponse, response);
    }
}
