package com.example.consumer.config;

import com.example.consumer.model.dto.PacienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class PacienteDeserializer extends JsonDeserializer<PacienteDTO> {

    private static ObjectMapper objMapper = new ObjectMapper();

    public PacienteDeserializer() {
        super(PacienteDTO.class, objMapper, false);
    }

}
