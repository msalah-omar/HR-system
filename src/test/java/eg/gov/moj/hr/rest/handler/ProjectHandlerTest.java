package eg.gov.moj.hr.rest.handler;


import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.ProjectDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Dto.commen.PaginationDto;
import com.example.hrSystem.Service.ProjectService;
import com.example.hrSystem.entity.Department;
import com.example.hrSystem.entity.Project;
import com.example.hrSystem.handler.ProjectHandler;
import com.example.hrSystem.mapper.PaginationMapper;
import com.example.hrSystem.mapper.ProjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ProjectHandlerTest
{
    private ProjectService projectService = mock(ProjectService.class);
    private ProjectMapper mapper = mock(ProjectMapper.class);
    private PaginationMapper paginationMapper = mock(PaginationMapper.class);

    private ProjectHandler projectHandler = new ProjectHandler(projectService, mapper, paginationMapper);

    @Test
    public void testGetAll()
    {
        List<Project> projectList = mock(List.class);
        List<ProjectDto> projectDtoList= mock(List.class);
        Page<Project> projectPage = mock(Page.class);
        PaginationDto paginationDto = mock(PaginationDto.class);
        Project project = mock(Project.class);

        Mockito.when(projectPage.getContent()).thenReturn(projectList);
        Mockito.when(paginationMapper.toPaginationDto(projectPage)).thenReturn(paginationDto);
        Mockito.when(projectService.getAll(0, 10)).thenReturn(projectPage);
        Mockito.when(mapper.toDto(projectList)).thenReturn(projectDtoList);

        PaginatedResultDto<ProjectDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(projectDtoList);
        paginatedResult.setPagination(paginationDto);

        ResponseEntity<?> response = projectHandler.getAll(0, 10);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().body(paginatedResult);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetById()
    {
        Project project = mock(Project.class);
        ProjectDto projectDto = mock(ProjectDto.class);

        Mockito.when(projectService.getById(project.getId())).thenReturn(Optional.of(project));
        Mockito.when(mapper.toDto(project)).thenReturn(projectDto);

        ResponseEntity<?> response = projectHandler.getById(project.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(projectDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testSave()
    {
        Project project = mock(Project.class);
        ProjectDto projectDto = mock(ProjectDto.class);

        Mockito.when(mapper.toEntity(projectDto)).thenReturn(project);
        Mockito.when(projectService.save(project)).thenReturn(project);
        Mockito.when(mapper.toDto(project)).thenReturn(projectDto);

        ResponseEntity<?> response = projectHandler.save(projectDto);
        ResponseEntity<?> expectedResponse = ResponseEntity.created(URI.create("/project/" + project.getId())).body(projectDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testUpdate()
    {
        Project project = mock(Project.class);
        ProjectDto projectDto = mock(ProjectDto.class);

        Mockito.when(projectService.getById(project.getId())).thenReturn(Optional.of(project));
        Mockito.when(mapper.updateEntityFromDto(projectDto, project)).thenReturn(project);

        ResponseEntity<?> response = projectHandler.update(project.getId(), projectDto);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();

        assertEquals(expectedResponse, response);
    }

    @Test
    void testDelete()
    {
        Project project = mock(Project.class);

        Mockito.when(projectService.getById(project.getId())).thenReturn(Optional.of(project));

        ResponseEntity<?> response = projectHandler.delete(project.getId());
        ResponseEntity<?> expectedResponse = ResponseEntity.noContent().build();

        assertEquals(expectedResponse, response);
    }


}
