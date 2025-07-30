package com.marina.projeto.bancario.strategy;

import com.marina.projeto.bancario.model.ResultadoTransferencia;
import com.marina.projeto.bancario.model.Transferencia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Service
public class RegraTED implements RegraTransferencia {

    @Override
    public ResultadoTransferencia processar(Transferencia transferencia) {
        log.debug("Processando transferência TED: {}", transferencia);
        double valor = transferencia.getValor();
        LocalDateTime dataHora = transferencia.getDataHora();

        if (finalDeSemana(dataHora)) {
            log.warn("TED rejeitada: tentativa em final de semana.");
            return new ResultadoTransferencia(transferencia, "TED só pode ser feita em dias úteis!");
        }
        if (valor < 5000.00) {
            log.warn("TED rejeitada: valor abaixo de 5.000,00.");
            return new ResultadoTransferencia(transferencia, "Valor mínimo para TED é R$5.000,00");
        }
        LocalTime horarioLimite = LocalTime.of(17, 0);
        LocalDateTime dataCredito;

        if (dataHora.toLocalTime().isBefore(horarioLimite)) {
            dataCredito = dataHora.toLocalDate().atTime(18, 0);
        } else {
            dataCredito = dataHora.toLocalDate().atTime(18, 0);
        }
        return new ResultadoTransferencia(transferencia, dataCredito,null);
    }

    private boolean finalDeSemana(LocalDateTime data) {
        DayOfWeek dia = data.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }
    private LocalDateTime proximoDiaUtil(LocalDateTime data) {
        LocalDateTime proximo = data.plusDays(1);
        while (finalDeSemana(proximo)) {
            proximo = proximo.plusDays(1);
        }
        return proximo;
    }

}
