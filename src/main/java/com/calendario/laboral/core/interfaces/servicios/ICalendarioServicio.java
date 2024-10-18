package com.calendario.laboral.core.interfaces.servicios;

import java.util.List;

import com.calendario.laboral.dominio.Calendario;

public interface ICalendarioServicio {
    
    boolean generarCalendario(int año);

    List<Calendario> obtenerCalendarioPorAño(int año);
}
