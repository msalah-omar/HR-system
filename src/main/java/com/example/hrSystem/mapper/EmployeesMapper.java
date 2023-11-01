package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.EmployeesDto;
import com.example.hrSystem.entity.Employees;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeesMapper
{
    EmployeesDto toDto(Employees baseEntityPram);
    Employees toEntity(EmployeesDto baseDtoPram);

    List<EmployeesDto> toDto(List<Employees> baseEntityPram);
    List<Employees> toEntity(List<EmployeesDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Employees updateEntityFromDto (EmployeesDto employeesDto, @MappingTarget Employees employees);
}
