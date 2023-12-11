package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.GenericDto;
import lombok.Data;



import java.time.LocalDate;
@Data
public class TimeSheetDetailDto extends GenericDto
{

    private LocalDate date;
    private LocalDate overtimeDate;
    private LocalDate taskId;
    private LocalDate taskDesc;
    private Boolean approvalStatus;
    private Boolean billable;
    private String comments;
    private Float overtimeHours;
    private Float startTime;
    private Float endTime;
    private Float timeHours;
    private EmployeesDto employees;

}
