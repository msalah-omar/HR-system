//package com.example.hrSystem.entity;
//
//import com.example.hrSystem.entity.commen.JPAEntity;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Data
//@Table(name = "time_sheet")
//public class TimeSheet extends JPAEntity
//{
//    @Column( name = "date")
//    private LocalDate date;
//
//    @Column(name = "approval_status")
//    private Boolean approvalStatus;
//
//    @Column(name = "comments")
//    private String comments;
//
//    @Column( name = "number_of_hours")
//    private Float numberOfHours;
//
//    @ManyToOne (fetch = FetchType.LAZY)
//    @JoinColumn(name = "employees_id")
//    private Employees employees;
//}
