package org.example.lab720202269.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "funciones")
public class Funcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "obraId", nullable = true)
    private Obra obra;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = true)
    private Room room;

    @Column(nullable = false)
    private Date funcionDate;

    @Column(name = "availableSeats")
    private Integer availableSeats;

}