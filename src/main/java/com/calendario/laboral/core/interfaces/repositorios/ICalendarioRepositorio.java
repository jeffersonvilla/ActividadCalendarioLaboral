package com.calendario.laboral.core.interfaces.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario.laboral.dominio.Calendario;

@Repository
public interface ICalendarioRepositorio extends JpaRepository<Calendario, Long>{
    
    boolean existsByFechaBetween(LocalDate inicio, LocalDate fin);

    List<Calendario> findByFechaBetween(LocalDate inicio, LocalDate fin);
}
