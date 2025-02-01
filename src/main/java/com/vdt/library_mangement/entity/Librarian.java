package com.vdt.library_mangement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "librarian")
@Data
public class Librarian {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    @Column(name = "workplace")
    private String workplace;
}