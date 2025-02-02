package com.vdt.library_mangement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "reader_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderRequest {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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