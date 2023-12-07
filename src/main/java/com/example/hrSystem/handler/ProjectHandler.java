package com.example.hrSystem.handler;


import com.example.hrSystem.Dto.ProjectDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.ProjectService;
import com.example.hrSystem.entity.Branches;
import com.example.hrSystem.entity.Project;
import com.example.hrSystem.exception.ErrorCodes;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.exception.ResourceRelatedException;
import com.example.hrSystem.exception.Response;
import com.example.hrSystem.mapper.PaginationMapper;
import com.example.hrSystem.mapper.ProjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProjectHandler
{
    private ProjectService projectService;
    private ProjectMapper mapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(ProjectDto dto )
    {

        Project project = mapper.toEntity(dto);
        projectService.save(project);
        return ResponseEntity.ok(mapper.toDto(project));

    }

    public ResponseEntity<?> getAll( Integer page , Integer size)
    {
        Page<Project> projects = projectService.getAll(page, size);
        List<ProjectDto> dtos = mapper.toDto(projects.getContent());
        PaginatedResultDto<ProjectDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(projects));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)
    {
        Project project = projectService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Project.class.getSimpleName(),id));
        ProjectDto projectDto = mapper.toDto(project);
        return ResponseEntity.ok(projectDto);
    }

    public ResponseEntity<?> update(Integer id, ProjectDto projectDto)
    {

        Project project=projectService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Branches.class.getSimpleName(),id));
        mapper.updateEntityFromDto(projectDto, project);
        projectService.update(project);
        ProjectDto dto = mapper.toDto(project);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)

    {
        Project project = projectService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Project.class.getSimpleName(), id));
        try
        {
            projectService.delete(project);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(Project.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
