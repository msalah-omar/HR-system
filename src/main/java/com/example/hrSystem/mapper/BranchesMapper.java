package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.BranchesDto;
import com.example.hrSystem.entity.Branches;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchesMapper
{
    BranchesDto toDto(Branches baseEntityPram);
    Branches toEntity(BranchesDto baseDtoPram);

    List<BranchesDto> toDto(List<Branches> baseEntityPram);
    List<Branches> toEntity(List<BranchesDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Branches updateEntityFromDto (BranchesDto branchesDto, @MappingTarget Branches branches);
}
