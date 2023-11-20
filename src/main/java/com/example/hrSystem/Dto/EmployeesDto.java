package com.example.hrSystem.Dto;


import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EmployeesDto
{
    @NotBlank(message = "Name is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Size(max = 205, message = "Name's max length allowed is 205 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String name;

    private String gender;
    private Integer age;
    private String dateOfBirth;
    @Pattern(regexp = "[0-9]{11}", message = "Phone Number must be 11 number and must be numbers", groups = {InsertValidation.class, UpdateValidation.class})
    @NotBlank(message = "Phone number is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    private String mobile;
    private String maritalStatus;

    @NotBlank(message = "National id is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Pattern(regexp = "[0-9]{14}", message = "National Id length allowed is 14 characters and must be Numbers", groups = {InsertValidation.class, UpdateValidation.class})
    private String nationalId;
    private String educationalQualification;

    @Size(max = 60, message = "Address's max length allowed is 60 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String address;
    private String directManager;
    private BranchesDto branches;
    private DepartmentDto department;

}
