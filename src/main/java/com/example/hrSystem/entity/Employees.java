package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Employees")
public class Employees extends JPAEntity
{
    @Column(name = "NAME")
    private String name;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "EDUCATIONAL_QUALIFICATION")
    private String educationalQualification;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DIRECT_MANAGER")
    private String directManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branches branches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;


}
