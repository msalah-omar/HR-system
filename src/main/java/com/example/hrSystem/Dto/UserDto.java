package com.example.hrSystem.Dto;

import com.example.hrSystem.Dto.commen.RestDto;
import com.example.hrSystem.validation.InsertValidation;
import com.example.hrSystem.validation.UpdateValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto extends RestDto
{
    @NotBlank(message = "User Name is mandatory", groups = {InsertValidation.class})
    @Size(max = 100, message = "User Name's max length allowed is 100 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String username;

    @NotBlank(message = "Arabic Name is mandatory", groups = {InsertValidation.class})
    @Size(max = 100, message = "Arabic Name's max length allowed is 100 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String arabicName;

    @NotBlank(message = "English Name is mandatory", groups = {InsertValidation.class})
    @Size(max = 100, message = "English Name's max length allowed is 100 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String englishName;

    @NotBlank(message = "Password is mandatory", groups = {InsertValidation.class})
    @Size(max = 50, min = 3, message = "Password's max length allowed is 50 characters and min length allowed is 3 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String password;

    private Boolean isActive;


    private Boolean isTemporaryPassword;

    private Set<RoleDto> roles;

    @JsonIgnore
    @JsonProperty(value = "user_password")
    public String getPassword() {
        return password;
    }

}
