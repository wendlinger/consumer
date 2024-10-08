package com.example.consumer.mappers;

import com.example.consumer.model.dto.PacienteDTO;
import com.example.consumer.model.entity.PacienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteDTO toDTO(PacienteEntity entity);

    List<PacienteDTO> toListDTO(List<PacienteEntity> dtoList);

    @Mapping(target = "codigo", ignore = true)
    PacienteEntity toEntity(PacienteDTO dto);

    @Mapping(target = "codigo", ignore = true)
    List<PacienteEntity> toListEntity(List<PacienteDTO> entityList);
}
