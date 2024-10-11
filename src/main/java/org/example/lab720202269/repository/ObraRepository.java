package org.example.lab720202269.repository;

import org.example.lab720202269.entity.Funcion;
import org.example.lab720202269.entity.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
}
