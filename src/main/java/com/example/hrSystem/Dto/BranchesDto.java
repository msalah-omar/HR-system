package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.GenericDto;
import lombok.Data;

@Data
public class BranchesDto extends GenericDto
{
    private String arabicName;
    private String englishName;
    private String address;
}
