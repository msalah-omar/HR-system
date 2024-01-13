package eg.gov.moj.hr.rest.handler;

import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Dto.commen.PaginationDto;
import com.example.hrSystem.Service.DepartmentService;
import com.example.hrSystem.entity.Department;
import com.example.hrSystem.handler.DepartmentHandler;

import com.example.hrSystem.mapper.DepartmentMapper;
import com.example.hrSystem.mapper.PaginationMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class DepartmentHandlerTest
{
    private DepartmentService departmentService = mock(DepartmentService.class);
    private DepartmentMapper mapper = mock(DepartmentMapper.class);
    private PaginationMapper paginationMapper = mock(PaginationMapper.class);

    private DepartmentHandler departmentHandler = new DepartmentHandler(departmentService, mapper, paginationMapper);


    @Test
    public void testGetAll()
    {
        List<Department> departmentList = mock(List.class);
        List<DepartmentDto> departmentDtoList = mock(List.class);
        Page<Department> departmentPage = mock(Page.class);
        PaginationDto paginationDto = mock(PaginationDto.class);
        Department department = mock(Department.class);

        Mockito.when(departmentPage.getContent()).thenReturn(departmentList);
        Mockito.when(paginationMapper.toPaginationDto(departmentPage)).thenReturn(paginationDto);
        Mockito.when(departmentService.getAll(0, 10)).thenReturn(departmentPage);
        Mockito.when(mapper.toDto(departmentList)).thenReturn(departmentDtoList);

        PaginatedResultDto<DepartmentDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(departmentDtoList);
        paginatedResult.setPagination(paginationDto);

        ResponseEntity<?> response = departmentHandler.getAll(0, 10);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().body(paginatedResult);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetById()
    {
        Department department = mock(Department.class);
        DepartmentDto departmentDto = mock(DepartmentDto.class);

        Mockito.when(departmentService.getById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(mapper.toDto(department)).thenReturn(departmentDto);

        ResponseEntity<?> response = departmentHandler.getById(department.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(departmentDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testSave()
    {
        Department department = mock(Department.class);
        DepartmentDto departmentDto = mock(DepartmentDto.class);

        Mockito.when(mapper.toEntity(departmentDto)).thenReturn(department);
        Mockito.when(departmentService.save(department)).thenReturn(department);
        Mockito.when(mapper.toDto(department)).thenReturn(departmentDto);

        ResponseEntity<?> response = departmentHandler.save(departmentDto);
        ResponseEntity<?> expectedResponse = ResponseEntity.created(URI.create("/department/" + department.getId())).body(departmentDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testUpdate()
    {
        Department department = mock(Department.class);
        DepartmentDto departmentDto = mock(DepartmentDto.class);

        Mockito.when(departmentService.getById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(mapper.updateEntityFromDto(departmentDto, department)).thenReturn(department);

        ResponseEntity<?> response = departmentHandler.update(department.getId(), departmentDto);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();

        assertEquals(expectedResponse, response);
    }


    @Test
    void testDelete()
    {
        Department department = mock(Department.class);

        Mockito.when(departmentService.getById(department.getId())).thenReturn(Optional.of(department));

        ResponseEntity<?> response = departmentHandler.delete(department.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.noContent().build();

        assertEquals(expectedResponse, response);
    }

}
