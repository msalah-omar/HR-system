package com.example.hrSystem.Dto.commen;

import lombok.Data;

@Data
public class LookupDto extends RestDto {
    private String arabicName;
    private String englishName;
    private String code;
    private boolean enabled;
}
