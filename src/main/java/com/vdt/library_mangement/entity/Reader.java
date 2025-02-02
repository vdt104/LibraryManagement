package com.vdt.library_mangement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "reader")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "document_copy_code")
    )
    private Set<DocumentCopy> documentCopies;
}