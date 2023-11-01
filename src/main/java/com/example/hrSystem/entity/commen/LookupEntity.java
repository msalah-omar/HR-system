package com.example.hrSystem.entity.commen;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class LookupEntity extends JPAEntity
{
    private String arabicName;
    private String englishName;
    private String code;
    private boolean enabled;


}
