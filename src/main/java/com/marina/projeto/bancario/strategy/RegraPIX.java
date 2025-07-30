package com.marina.projeto.bancario.strategy;

import com.marina.projeto.bancario.model.ResultadoTransferencia;
import com.marina.projeto.bancario.model.Transferencia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Slf4j
@Service
public class RegraPIX implements RegraTransferencia{

    @Override
    public ResultadoTransferencia processar(Transferencia transferencia) {
        log.debug("Processando transferência PIX: {}", transferencia);

        double valor = transferencia.getValor();
        LocalDateTime dataHora = transferencia.getDataHora();

        if (valor <= 0) {
            log.warn("TED rejeitada: valor abaixo de 0,00.");
            return new ResultadoTransferencia(transferencia, "Valor da transferência Pix deve ser maior que zero.");
        }

        LocalDateTime dataCredito = dataHora.plusSeconds(5); 

        return new ResultadoTransferencia(transferencia, dataCredito, null);
    }
}
