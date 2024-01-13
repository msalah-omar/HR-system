package com.example.hrSystem.entity;


import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "vacation")
public class Vacation extends JPAEntity
{

    @Column(name = "number_of_vacation")
    private Integer numberOfVacation;

    @Column(name = "vacation_date")
    private LocalDate vacationDate;

}
