package com.example.hrSystem.mapper;


import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.entity.Department;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper
{
    DepartmentDto toDto(Department baseEntityPram);
    Department toEntity(DepartmentDto baseDtoPram);

    List<DepartmentDto> toDto(List<Department> baseEntityPram);
    List<Department> toEntity(List<DepartmentDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Department updateEntityFromDto (DepartmentDto departmentDto, @MappingTarget Department department);

}
