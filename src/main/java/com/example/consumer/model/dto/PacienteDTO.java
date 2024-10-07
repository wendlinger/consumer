package com.example.consumer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class PacienteDTO {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

}
