package com.example.hrSystem.mapper;


import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.DocumentDto;
import com.example.hrSystem.entity.Department;
import com.example.hrSystem.entity.Document;
import org.mapstruct.*;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DocumentMapper {

//   @Mappings({@Mapping(source = "content", target = "content", ignore = true) })
    public abstract DocumentDto toDto(Document entity);

    public abstract List<DocumentDto> toDto(List<Document> entityList);

    @AfterMapping
    public void toDtoAfterMapping(Document entity, @MappingTarget DocumentDto dto) {
      //  dto.setContent(Base64.getEncoder().withoutPadding().encodeToString(entity.getContent()));
    }

    @InheritInverseConfiguration
    public abstract Document toEntity(DocumentDto dto);

    public abstract List<Document> toEntity(List<DocumentDto> dtoList);


    @AfterMapping
    public void toEntityAfterMapping(DocumentDto dto, @MappingTarget Document entity) {
        //entity.setContent(Base64.getDecoder().decode(dto.getContent()));
    }

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Document updateEntityFromDto (DocumentDto documentDto, @MappingTarget Document document);

}
