package com.calendario.laboral.aplicacion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.calendario.laboral.core.interfaces.repositorios.ICalendarioRepositorio;
import com.calendario.laboral.core.interfaces.repositorios.ITipoRepositorio;
import com.calendario.laboral.core.interfaces.servicios.ICalendarioServicio;
import com.calendario.laboral.dominio.Calendario;
import com.calendario.laboral.dominio.Tipo;
import com.calendario.laboral.dominio.DTOs.FestivoRequestDto;

@Service
public class CalendarioServicio implements ICalendarioServicio{

    private ICalendarioRepositorio calendarioRepositorio;
    private ITipoRepositorio tipoRepositorio;
    private FestivosCliente festivosCliente;

    public CalendarioServicio(
        ICalendarioRepositorio calendarioRepositorio,
        ITipoRepositorio tipoRepositorio,
        FestivosCliente festivosCliente
        ){

        this.calendarioRepositorio = calendarioRepositorio;
        this.tipoRepositorio = tipoRepositorio;
        this.festivosCliente = festivosCliente;
    }

    @Override
    public boolean generarCalendario(int año) {

        LocalDate inicioAño = LocalDate.of(año, 1, 1);
        LocalDate finAño = LocalDate.of(año, 12, 31);

        // Verificar si ya existe el calendario para el año
        // Solo guarda en base de datos si no existe aun
        if (!calendarioRepositorio.existsByFechaBetween(inicioAño, finAño)) {
         
            List<FestivoRequestDto> festivos = festivosCliente.obtenerFestivos(año);

            Set<LocalDate> diasFestivos = festivos.stream().map(festivo -> festivo.getFecha()).collect(Collectors.toSet());

            Tipo tipoLaboral = tipoRepositorio.findByTipo("Día laboral").get();
            Tipo tipoFinDeSemana = tipoRepositorio.findByTipo("Fin de Semana ").get();
            Tipo tipoFestivo = tipoRepositorio.findByTipo("Día festivo ").get();

            // Itera sobre todos los días del año
            for (int mes = 1; mes <= 12; mes++) {
                YearMonth yearMonth = YearMonth.of(año, mes);
                for (int dia = 1; dia <= yearMonth.lengthOfMonth(); dia++) {
                    LocalDate fecha = LocalDate.of(año, mes, dia);
                    Calendario calendario = new Calendario();
                    calendario.setFecha(fecha);

                    // Clasificación de los días
                    if (diasFestivos.contains(fecha)) {
                        calendario.setTipo(tipoFestivo);
                    } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        calendario.setTipo(tipoFinDeSemana);
                    } else {
                        calendario.setTipo(tipoLaboral);
                    }

                    // Establecer la descripción con el nombre del día
                    String nombreDia = fecha.getDayOfWeek()
                                            .getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
                    calendario.setDescripcion(nombreDia);

                    // Guardar en la base de datos
                    calendarioRepositorio.save(calendario);
                }
            }
        }

        return true;
        
    }

    @Override
    public List<Calendario> obtenerCalendarioPorAño(int año) {
        // Definir las fechas de inicio y fin del año
        LocalDate inicioAño = LocalDate.of(año, 1, 1);
        LocalDate finAño = LocalDate.of(año, 12, 31);

        // Consultar los días del calendario para el año dado
        List<Calendario> calendario = calendarioRepositorio.findByFechaBetween(inicioAño, finAño);

        return calendario;
    }

}
