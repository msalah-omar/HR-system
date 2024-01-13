package com.example.hrSystem.Dto;

import lombok.Data;


import java.time.LocalDate;


@Data
public class VacationDto
{
    private Integer numberOfVacation;
    private LocalDate vacationDate;

}
