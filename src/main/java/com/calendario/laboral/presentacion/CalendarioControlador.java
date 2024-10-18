package com.calendario.laboral.presentacion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.laboral.core.interfaces.servicios.ICalendarioServicio;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {
    
    private ICalendarioServicio calendarioServicio;

    public CalendarioControlador(ICalendarioServicio calendarioServicio){
        this.calendarioServicio = calendarioServicio;
    }

    @GetMapping("/generar/{anio}")
    public boolean generarCalendario(@PathVariable int anio){
        return calendarioServicio.generarCalendario(anio);
    }
}
