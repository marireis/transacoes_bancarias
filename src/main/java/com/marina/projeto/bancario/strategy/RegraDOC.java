package com.marina.projeto.bancario.strategy;

import com.marina.projeto.bancario.model.ResultadoTransferencia;
import com.marina.projeto.bancario.model.Transferencia;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RegraDOC implements RegraTransferencia {

    @Override
    public ResultadoTransferencia processar(Transferencia transferencia) {
        double valor = transferencia.getValor();
        LocalDateTime dataHora = transferencia.getDataHora();

        if (finalDeSemana(dataHora)) {
            return new ResultadoTransferencia(transferencia, "DOC só pode ser feito em dias úteis!");
        }

        if (valor < 1.00) {
            return new ResultadoTransferencia(transferencia, "Valor mínimo para DOC é R$1,00");
        }

        if (valor > 4999.99) {
            return new ResultadoTransferencia(transferencia, "Valor máximo para DOC é R$4.999,99");
        }

        LocalTime horarioLimite = LocalTime.of(22, 0);
        LocalDateTime dataCredito;

        if (dataHora.toLocalTime().isBefore(horarioLimite)) {
            dataCredito = proximoDiaUtil(dataHora).toLocalDate().atTime(9, 0);
        } else {
            dataCredito = proximoDiaUtil(dataHora.plusDays(1)).toLocalDate().atTime(9, 0);
        }

        return new ResultadoTransferencia(transferencia, dataCredito, null);
    }

    private boolean finalDeSemana(LocalDateTime data) {
        DayOfWeek dia = data.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }

    private LocalDateTime proximoDiaUtil(LocalDateTime data) {
        LocalDateTime proximo = data;
        while (finalDeSemana(proximo)) {
            proximo = proximo.plusDays(1);
        }
        return proximo;
    }

}
