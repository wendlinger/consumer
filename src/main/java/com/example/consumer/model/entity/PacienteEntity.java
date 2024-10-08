package com.example.consumer.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "CONSUMER", name = "PACIENTE")
@AllArgsConstructor
@NoArgsConstructor
public class PacienteEntity implements Serializable {

    private static final long serialVersionUID = -4669685425460097723L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O CPF não pode estar em branco")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos numéricos")
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de nascimento deve ser uma data aterior")
    private LocalDate dataNascimento;

}
