package com.example.consumer.service;

import com.example.consumer.exceptions.NotFoundException;
import com.example.consumer.mappers.PacienteMapper;
import com.example.consumer.model.dto.PacienteDTO;
import com.example.consumer.model.entity.PacienteEntity;
import com.example.consumer.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;
    private final PacienteMapper pacienteMapper;

    @Transactional
    public PacienteEntity save(PacienteDTO pacienteDTO) {
        var pacienteEntity = pacienteMapper.toEntity(pacienteDTO);
        return repository.save(pacienteEntity);
    }

    public PacienteDTO findById(Long id) {
        var pacienteDB = repository.findById(id);
        if (!pacienteDB.isPresent()) {
            throw new NotFoundException("Paciente not found by ID: " + id);
        }
        return pacienteMapper.toDTO(pacienteDB.get());
    }

    public List<PacienteDTO> findAll() {
        var pacienteListDB = repository.findAll();
        return pacienteMapper.toListDTO(pacienteListDB);
    }
}
