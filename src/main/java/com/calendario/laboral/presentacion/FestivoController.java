import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/festivo")

public class FestivoController {

    @Autowired
    private FestivoService festivoService;

    /**
     * Endpoint que expone la lista de festivos de un año
     * 
     * @param anio El año para el cual se desea obtener los festivos
     * @return Lista de festivos del año
     */
    @GetMapping("/obtener/{anio}")
    public List<Map<String, Object>> obtenerFestivos(@PathVariable int anio) {
        return festivoService.obtenerFestivosPorAnio(anio);
    }
}