package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.TimeSheetDetailDto;
import com.example.hrSystem.entity.TimeSheetDetail;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeSheetDetailMapper
{
    TimeSheetDetailDto toDto(TimeSheetDetail baseEntityPram);
    TimeSheetDetail toEntity(TimeSheetDetailDto baseDtoPram);

    List<TimeSheetDetailDto> toDto(List<TimeSheetDetail> baseEntityPram);
    List<TimeSheetDetail> toEntity(List<TimeSheetDetailDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract TimeSheetDetail updateEntityFromDto (TimeSheetDetailDto timeSheetDetailDto, @MappingTarget TimeSheetDetail timeSheetDetail);
}
