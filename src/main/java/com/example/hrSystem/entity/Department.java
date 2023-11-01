package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "department")

public class Department extends JPAEntity
{
    @Column(name = "ARABIC_NAME")
    private String arabicName;

    @Column(name = "ENGLISH_NAME")
    private String englishName;
}
