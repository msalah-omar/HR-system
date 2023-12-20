package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.GenericDto;
import lombok.Data;


import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TimeSheetDetailDto extends GenericDto
{




    private LocalDateTime startDate;


    private LocalDateTime endDate;

    private Float overtimeHours;

    private String taskDesc;

    private Boolean approvalStatus;

    private Boolean billable;

    private String comments;
    private EmployeesDto employees;

}
