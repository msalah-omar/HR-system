package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.RestDto;
import lombok.Data;

@Data
public class DocumentTypeDto extends RestDto
{
    private String arabicName;
    private String englishName;
}
