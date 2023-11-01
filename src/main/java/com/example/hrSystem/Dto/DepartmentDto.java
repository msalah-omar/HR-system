package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.GenericDto;
import lombok.Data;

@Data
public class DepartmentDto extends GenericDto
{
    private String arabicName;
    private String englishName;
}
