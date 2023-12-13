package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "time_sheet_detail")
public class TimeSheetDetail extends JPAEntity
{
    @Column( name = "date")
    private LocalDate date;

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

    @Column( name = "start_time")
    private Float startTime;

    @Column( name = "end_time")
    private Float endTime;

    @Column( name = "time_hours")
    private Float timeHours;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_id")
    private Employees employees;

}
