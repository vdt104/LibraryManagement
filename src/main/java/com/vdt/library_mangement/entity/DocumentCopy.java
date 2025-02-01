package com.vdt.library_mangement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "document_copy")
@Data
public class DocumentCopy {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        AVAILABLE, NOT_AVAILABLE, BORROWED
    }
}