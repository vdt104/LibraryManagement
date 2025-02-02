package com.vdt.library_mangement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reader_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderCard {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Reader reader;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public enum Status {
        REQUESTED, ACTIVE, EXPIRED, REQUEST_EXTEND
    }
}