package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.DocumentTypeDto;
import com.example.hrSystem.Dto.EmployeesDto;
import com.example.hrSystem.entity.DocumentType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")

public interface DocumentTypeMapper
{
    DocumentTypeDto toDto(DocumentType baseEntityPram);
    DocumentType toEntity(DocumentTypeDto baseDtoPram);

    List<DocumentTypeDto> toDto(List<DocumentType> baseEntityPram);
    List<DocumentType> toEntity(List<DocumentTypeDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract DocumentType updateEntityFromDto (DocumentTypeDto documentTypeDto, @MappingTarget DocumentType documentType);

}
