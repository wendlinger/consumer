package com.example.consumer.controller;

import com.example.consumer.exceptions.NotFoundException;
import com.example.consumer.model.dto.PacienteDTO;
import com.example.consumer.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pacientes")
public class PacienteControler {

    private final PacienteService pacienteService;

    @Operation(summary = "Método usado para buscar paciente por id")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        try {
            var pacienteDB = pacienteService.findById(id);
            return ResponseEntity.ok(pacienteDB);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Método usado para buscar todos os pacientes")
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> findAll() {
        return ResponseEntity.ok(pacienteService.findAll());
    }
}
