package com.marina.projeto.bancario.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferenciaDTO {

    @NotNull
    private String tipo;
    @NotNull
    @Positive
    private double valor;

    private String origem;
    private String destino;
    private String tipoConta;
}
