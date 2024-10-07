package com.example.consumer.mappers;

import com.example.consumer.model.dto.PacienteDTO;
import com.example.consumer.model.entity.PacienteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public class PacienteMapper implements EntityMapper<PacienteDTO, PacienteEntity> {

    @Override
    public PacienteDTO toDTO(PacienteEntity entity) {
        return null;
    }

    @Override
    public List<PacienteDTO> toListDTO(List<PacienteEntity> dtoList) {
        return List.of();
    }

    @Override
    public PacienteEntity toEntity(PacienteDTO dto) {
        return null;
    }

    @Override
    public List<PacienteEntity> toListEntity(List<PacienteDTO> entityList) {
        return List.of();
    }
}
