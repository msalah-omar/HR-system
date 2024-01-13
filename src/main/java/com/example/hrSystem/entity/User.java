package com.example.hrSystem.entity;


import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")
public class User extends JPAEntity
{

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "ARABIC_NAME" ,columnDefinition = "NVARCHAR(255)")
    private String arabicName;

    @Column(name = "ENGLISH_NAME")
    private String englishName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "IS_TEMPORARY_PASSWORD")
    private Boolean isTemporaryPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")}
    )
    private Set<Role> roles;


}
