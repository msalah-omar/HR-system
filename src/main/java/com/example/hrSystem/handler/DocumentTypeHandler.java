package com.example.hrSystem.handler;

import com.example.hrSystem.Dto.DepartmentDto;
import com.example.hrSystem.Dto.DocumentTypeDto;
import com.example.hrSystem.Dto.commen.PaginatedResultDto;
import com.example.hrSystem.Service.DepartmentService;
import com.example.hrSystem.Service.DocumentTypeService;
import com.example.hrSystem.entity.Department;
import com.example.hrSystem.entity.DocumentType;
import com.example.hrSystem.exception.ErrorCodes;
import com.example.hrSystem.exception.ResourceNotFoundException;
import com.example.hrSystem.exception.ResourceRelatedException;
import com.example.hrSystem.exception.Response;
import com.example.hrSystem.mapper.DepartmentMapper;
import com.example.hrSystem.mapper.DocumentTypeMapper;
import com.example.hrSystem.mapper.PaginationMapper;
import liquibase.pro.packaged.R;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DocumentTypeHandler
{

    private DocumentTypeService documentTypeService;
    private DocumentTypeMapper mapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(DocumentTypeDto dto)
    {
        DocumentType documentType = mapper.toEntity(dto);
        documentTypeService.save(documentType);
        return ResponseEntity.ok(mapper.toDto(documentType));

    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        Page<DocumentType> documentTypes = documentTypeService.getAll(page, size);
        List<DocumentTypeDto> dtos = mapper.toDto(documentTypes.getContent());
        PaginatedResultDto<DocumentTypeDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(documentTypes));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)
    {
        DocumentType documentType = documentTypeService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(DocumentType.class.getSimpleName(),id));
        DocumentTypeDto documentTypeDto = mapper.toDto(documentType);
        return ResponseEntity.ok(documentTypeDto);
    }

    public ResponseEntity<?> update(Integer id, DocumentTypeDto documentTypeDto)
    {

        DocumentType documentType=documentTypeService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Department.class.getSimpleName(),id));
        mapper.updateEntityFromDto(documentTypeDto, documentType);
        documentTypeService.update(documentType);
        DocumentTypeDto dto = mapper.toDto(documentType);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        DocumentType documentType = documentTypeService.getById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentType.class.getSimpleName(), id));
        try
        {
            documentTypeService.delete(documentType);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(DocumentType.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
