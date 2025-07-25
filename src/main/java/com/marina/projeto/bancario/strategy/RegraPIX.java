package com.marina.projeto.bancario.strategy;

import com.marina.projeto.bancario.model.ResultadoTransferencia;
import com.marina.projeto.bancario.model.Transferencia;

import java.time.LocalDateTime;

public class RegraPIX implements RegraTransferencia{

    @Override
    public ResultadoTransferencia processar(Transferencia transferencia) {
        double valor = transferencia.getValor();
        LocalDateTime dataHora = transferencia.getDataHora();

        if (valor <= 0) {
            return new ResultadoTransferencia(transferencia, "Valor da transferência Pix deve ser maior que zero.");
        }

        LocalDateTime dataCredito = dataHora.plusSeconds(5); 

        return new ResultadoTransferencia(transferencia, dataCredito, null);
    }
}
