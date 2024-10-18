package com.calendario.laboral.core.interfaces.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario.laboral.dominio.Tipo;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Long>{
 
    Optional<Tipo> findByTipo(String tipo);
}
