package com.calendario.laboral.dominio.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FestivoRequestDto {

    private String nombre;
    private LocalDate fecha;

}
