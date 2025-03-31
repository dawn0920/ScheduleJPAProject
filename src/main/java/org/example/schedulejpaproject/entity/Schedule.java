package org.example.schedulejpaproject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "schedule")

public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
