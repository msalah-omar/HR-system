package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import com.example.hrSystem.entity.commen.LookupEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "branches")
public class Branches extends LookupEntity
{


    @Column(name = "ADDRESS")
    private String address;


}
