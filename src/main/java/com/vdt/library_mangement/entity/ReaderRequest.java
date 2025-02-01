package com.vdt.library_mangement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "reader_request")
@Data
public class ReaderRequest {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_borrowed")
    private Date dateBorrowed;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_due")
    private Date dateDue;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_returned")
    private Date dateReturned;

    @Column(name = "penalty_fee")
    private Double penaltyFee;

    @Column(name = "notes")
    private String notes;

    @ManyToMany
    @JoinTable(
        name = "reader_request_detail",
        joinColumns = @JoinColumn(name = "reader_request_id"),
        inverseJoinColumns = @JoinColumn(name = "document_copy_code")
    )
    private Set<DocumentCopy> documentCopies;

    public enum Status {
        REQUESTED, ACCEPTED, REJECTED, BORROWED, RETURNED, OVERDUE, CANCELLED
    }
}