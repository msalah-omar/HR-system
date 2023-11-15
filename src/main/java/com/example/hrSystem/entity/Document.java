package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Document extends JPAEntity
{
    @Column
    private String name;

    @Column
    private String path;

    @Column
    private String mediaType ;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;
}
