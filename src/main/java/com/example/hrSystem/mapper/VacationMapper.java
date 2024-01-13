package com.example.hrSystem.mapper;


import com.example.hrSystem.Dto.VacationDto;
import com.example.hrSystem.entity.Vacation;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacationMapper
{
    VacationDto toDto(Vacation baseEntityPram);
    Vacation toEntity(VacationDto baseDtoPram);

    List<VacationDto> toDto(List<Vacation> baseEntityPram);
    List<Vacation> toEntity(List<VacationDto> baseDtoPram);




    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Vacation updateEntityFromDto (VacationDto vacationDto, @MappingTarget Vacation vacation);
}
