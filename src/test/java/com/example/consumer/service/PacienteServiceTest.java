package com.example.consumer.service;

import com.example.consumer.exceptions.NotFoundException;
import com.example.consumer.mappers.PacienteMapper;
import com.example.consumer.model.dto.PacienteDTO;
import com.example.consumer.model.entity.PacienteEntity;
import com.example.consumer.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @InjectMocks
    private PacienteService pacienteService;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private PacienteMapper pacienteMapper;

    private PacienteDTO pacienteDTO;
    private PacienteEntity pacienteEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pacienteDTO = PacienteDTO.builder().nome("João Silva").cpf("123.654.234-03").build();

        pacienteEntity = new PacienteEntity();
        pacienteEntity.setCodigo(1L);
        pacienteEntity.setNome("João Silva");
        pacienteEntity.setCpf("123.654.234-03");
    }

    @Test
    void testSave() {
        when(pacienteMapper.toEntity(any(PacienteDTO.class))).thenReturn(pacienteEntity);
        when(pacienteRepository.save(any(PacienteEntity.class))).thenReturn(pacienteEntity);

        PacienteEntity result = pacienteService.save(pacienteDTO);

        assertNotNull(result);
        assertEquals(pacienteEntity.getCpf(), result.getCpf());
        verify(pacienteRepository, times(1)).save(any(PacienteEntity.class));
    }

    @Test
    void testFindByIdPacienteExists() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEntity));
        when(pacienteMapper.toDTO(pacienteEntity)).thenReturn(pacienteDTO);

        PacienteDTO result = pacienteService.findById(1L);

        assertNotNull(result);
        assertEquals(pacienteDTO.getCpf(), result.getCpf());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdPacienteNotFound() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> pacienteService.findById(1L));
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        when(pacienteRepository.findAll()).thenReturn(Collections.singletonList(pacienteEntity));
        when(pacienteMapper.toListDTO(anyList())).thenReturn(Collections.singletonList(pacienteDTO));

        List<PacienteDTO> result = pacienteService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testFindAllEmptyList() {
        when(pacienteRepository.findAll()).thenReturn(Collections.emptyList());

        List<PacienteDTO> result = pacienteService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pacienteRepository, times(1)).findAll();
    }
}
