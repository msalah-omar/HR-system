package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "time_sheet_detail")
public class TimeSheetDetail extends JPAEntity
{
    @Column( name = "start_date")
    private LocalDateTime startDate;

    @Column( name = "end_date")
    private LocalDateTime endDate;

    @Column( name = "overtime_hours")
    private Float overtimeHours;

    @Column( name = "task_desc")
    private String taskDesc;

    @Column(name = "approval_status")
    private Boolean approvalStatus;

    @Column(name = "billable")
    private Boolean billable;

    @Column(name = "comments")
    private String comments;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_id")
    private Employees employees;

}
