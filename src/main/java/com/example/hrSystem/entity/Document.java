package com.example.hrSystem.entity;

import com.example.hrSystem.entity.commen.AuditingEntity;
import com.example.hrSystem.entity.commen.JPAEntity;
import lombok.Data;


import javax.persistence.*;

@Data
@Entity
public class Document extends AuditingEntity
{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_Type_id")
    private DocumentType documentType;

    @Column
    private String documentName;

    @Column
    private String contentType;

    @Column
    private Long size;

    @Column
    private byte[] content;

}
