package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "branches")
public class Branches extends JPAEntity
{

    @Column(name = "ARABIC_NAME")
    private String arabicName;

    @Column(name = "ENGLISH_NAME")
    private String englishName;

    @Column(name = "ADDRESS")
    private String address;


}
