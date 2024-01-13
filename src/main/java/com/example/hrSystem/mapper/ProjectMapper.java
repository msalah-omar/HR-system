package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.DocumentTypeDto;
import com.example.hrSystem.Dto.ProjectDto;
import com.example.hrSystem.entity.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper
{
    ProjectDto toDto(Project baseEntityPram);
    Project toEntity(ProjectDto baseDtoPram);

    List<ProjectDto> toDto(List<Project> baseEntityPram);
    List<Project> toEntity(List<ProjectDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Project updateEntityFromDto (ProjectDto projectDto, @MappingTarget Project project);

}
