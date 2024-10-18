package com.calendario.laboral.aplicacion;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.calendario.laboral.dominio.DTOs.FestivoRequestDto;

@Service
public class FestivosCliente {
    
    private RestTemplate restTemplate;

    public FestivosCliente(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<FestivoRequestDto> obtenerFestivos(int año){
        String url = "http://localhost:3000/festivos/obtener/"+ año;
        ResponseEntity<List<FestivoRequestDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, 
            new ParameterizedTypeReference<List<FestivoRequestDto>>() {
                
            }
        );
        return responseEntity.getBody();
    }

}
