package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.KindOfVacationDto;
import com.example.hrSystem.entity.KindOfVacation;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = "spring")
public interface KindOfVacationMapper
{
    KindOfVacationDto toDto(KindOfVacation baseEntityPram);
    KindOfVacation toEntity(KindOfVacationDto baseDtoPram);

    List<KindOfVacationDto> toDto(List<KindOfVacation> baseEntityPram);
    List<KindOfVacation> toEntity(List<KindOfVacationDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract KindOfVacation updateEntityFromDto (KindOfVacationDto kindOfVacationDto, @MappingTarget KindOfVacation kindOfVacation);
}
