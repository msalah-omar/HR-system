package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.LookupEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "project")
public class Project extends LookupEntity
{
}
