package com.calendario.laboral.dominio.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FestivoDto {

    private String nombre;
    private LocalDate fecha;

}
