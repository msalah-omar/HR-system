package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.RestDto;
import lombok.Data;

@Data
public class DocumentDto extends RestDto
{
    private String name;

    private String path;

    private String mediaType;

    private DocumentTypeDto documentType;

}
