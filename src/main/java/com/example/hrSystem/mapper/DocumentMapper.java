package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.DocumentDto;
import com.example.hrSystem.Service.DocumentTypeService;
import com.example.hrSystem.entity.Document;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DocumentMapper {


    @Autowired
    private DocumentTypeService documentTypeService;


    @Autowired
    private DocumentTypeMapper documentTypeMapper;


    @Mappings({
            @Mapping(source = "documentType", target = "documentType", ignore = true),
    })
    public abstract DocumentDto toDto(Document document);

    public abstract List<DocumentDto> toDto(List<Document> documentList);

//    @AfterMapping
//    public void toDtoAfterMapping(Document document, @MappingTarget DocumentDto dto) {
//
//        if (HibernateUtils.getHibernateQuery(document.getDocumentType())) {
//            dto.setDocumentType(documentTypeMapper.toDto(document.getDocumentType()));
//        }
//
//    }

    @InheritInverseConfiguration
    public abstract Document toEntity(DocumentDto dto);

    public abstract List<Document> toEntity(List<DocumentDto> dtoList);


    @AfterMapping
    public void toEntityAfterMapping(DocumentDto dto, @MappingTarget Document document) {

        if (dto.getDocumentType() != null) {
            document.setDocumentType(documentTypeService.getReferenceById(dto.getDocumentType().getId()));
        }
    }

    @InheritInverseConfiguration
    public abstract Document updateEntityFromDto(DocumentDto documentDto, @MappingTarget Document document);
}
