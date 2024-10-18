package com.calendario.laboral.core.interfaces.repositorios;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario.laboral.dominio.Calendario;

@Repository
public interface ICalendarioRepositorio extends JpaRepository<Calendario, Long>{
    
    boolean existsByFechaBetween(LocalDate inicio, LocalDate fin);
}
