package com.example.hrSystem.Dto.commen;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
public class GenericDto
{

    private Integer id;
    private Integer version;


}
