package com.vdt.library_mangement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "topic")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Topic {
    @Id
    private String code;

    @Column(name = "name", nullable = false)
    private String name;
}
