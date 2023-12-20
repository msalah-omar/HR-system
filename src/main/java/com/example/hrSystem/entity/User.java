package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
@Table(name = "user", schema = "public")
public class User extends JPAEntity
{
    @Column(name = "username")
    public String username;

    @Column(name = "phone")
    public String phone;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;

//    @Enumerated(EnumType.STRING)
//    private Role role;
}