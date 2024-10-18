package com.calendario.laboral.dominio.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FestivoResponseDto {
    
    private String festivo;
    private LocalDate fecha;
}
