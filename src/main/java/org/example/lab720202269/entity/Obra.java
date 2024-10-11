package org.example.lab720202269.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "obras")
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(name ="description")
    private String description;

    @Column(nullable = false)
    private int duration;

    @Column(name = "releaseDate")
    private Date releaseDate;

}