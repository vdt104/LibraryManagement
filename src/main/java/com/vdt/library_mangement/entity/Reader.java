package com.vdt.library_mangement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "reader")
@Data
public class Reader {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "student_id", unique = true)
    private String studentId;

    @OneToOne(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReaderCard readerCard;

    @ManyToMany
    @JoinTable(
        name = "reader_bookshelf",
        joinColumns = @JoinColumn(name = "reader_id"),
        inverseJoinColumns = @JoinColumn(name = "document_copy_code")
    )
    private Set<DocumentCopy> documentCopies;
}