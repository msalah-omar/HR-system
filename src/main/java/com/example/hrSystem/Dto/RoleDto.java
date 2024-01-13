package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.LookupDto;
import com.example.hrSystem.validation.InsertValidation;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class RoleDto extends LookupDto
{

    @NotBlank(message = "Code is mandatory", groups = {InsertValidation.class})
    private String code;

}
