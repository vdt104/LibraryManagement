package com.vdt.library_mangement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "document_code", nullable = false, unique = true)
    private String documentCode;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "topic_code", nullable = false)
    private Topic topic;

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "category")
    private String category;

    @Column(name = "year_published")
    private Integer yearPublished;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToMany
    @JoinTable(
        name = "document_author",
        joinColumns = @JoinColumn(name = "document_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
}