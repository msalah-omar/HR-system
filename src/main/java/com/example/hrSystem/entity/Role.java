package com.example.hrSystem.entity;



import com.example.hrSystem.entity.commen.LookupEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Role extends LookupEntity
{

    @Column
    private String code;
}
