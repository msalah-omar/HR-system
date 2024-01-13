package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.LookupEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "kind_of_vacation")
public class KindOfVacation extends LookupEntity
{
}
