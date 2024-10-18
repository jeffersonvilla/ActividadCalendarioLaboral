package com.calendario.laboral.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.laboral.aplicacion.FestivosCliente;
import com.calendario.laboral.dominio.DTOs.FestivoResponseDto;

import java.util.List;

@RestController
@RequestMapping("/festivos")
public class FestivoController {

    @Autowired
    private FestivosCliente festivosCliente;

    /**
     * Endpoint que expone la lista de festivos de un año
     * 
     * @param anio El año para el cual se desea obtener los festivos
     * @return Lista de festivos del año
     */
    @GetMapping("/obtener/{anio}")
    public List<FestivoResponseDto> obtenerFestivos(@PathVariable int anio) {
        return festivosCliente.obtenerFestivos(anio).stream()
            .map((festivo) -> new FestivoResponseDto(festivo.getNombre(), festivo.getFecha()))
            .toList();
    }
}