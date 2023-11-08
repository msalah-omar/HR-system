package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.RestDto;
import com.example.hrSystem.entity.DocumentType;
import lombok.Data;

@Data
public class DocumentDto extends RestDto
{
    private DocumentType documentType;

    private String documentName;


    private String contentType;

    private Long size;

    private byte[] content;
}
