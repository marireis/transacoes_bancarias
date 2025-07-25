package com.marina.projeto.bancario.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResultadoTransferencia {

    private boolean aceita;
    private LocalDateTime dataCredito;
    private String motivoRejeicao;
    private String tipoRisco;
    private Transferencia transferenciaOriginal;


    // transferências aceitas
    public ResultadoTransferencia(Transferencia transferenciaOriginal, LocalDateTime dataCredito, String tipoRisco) {
        this.aceita = true;
        this.dataCredito = dataCredito;
        this.tipoRisco = tipoRisco;
        this.transferenciaOriginal = transferenciaOriginal;
    }

    // transferências rejeitadas
    public ResultadoTransferencia(Transferencia transferenciaOriginal, String motivoRejeicao) {
        this.aceita = false;
        this.motivoRejeicao = motivoRejeicao;
        this.transferenciaOriginal = transferenciaOriginal;
    }

    public boolean isSucesso() {
        return aceita;
    }
}
