package com.marina.projeto.bancario.validator;

import com.marina.projeto.bancario.infra.TransferenciaException;
import com.marina.projeto.bancario.model.Transferencia;
import com.marina.projeto.bancario.strategy.RegraTED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaValidator {

    @Autowired
    private RegraTED regraTED;

    public void validar(Transferencia transferencia) {

        String tipo = transferencia.getTipo();
        if (tipo == null || tipo.isEmpty()) {
            throw new TransferenciaException("O tipo de transferência é obrigatório.");
        }
        if (!tipo.equals("PIX") && !tipo.equals("TED") && !tipo.equals("DOC")) {
            throw new TransferenciaException("Tipo de transferência inválido. Permitidos: PIX, TED, DOC.");
        }
        if (transferencia.getValor() <= 0) {
            throw new TransferenciaException("O valor da transferência deve ser maior que zero.");
        }
        if (transferencia.getOrigem() == null || transferencia.getOrigem().isEmpty()) {
            throw new TransferenciaException("A origem é obrigatória.");
        }
        if (transferencia.getDestino() == null || transferencia.getDestino().isEmpty()) {
            throw new TransferenciaException("O destino é obrigatório.");
        }
        if (transferencia.getTipoConta() == null || transferencia.getTipoConta().isEmpty()) {
            throw new TransferenciaException("O tipo de conta é obrigatório.");
        }
        if ("TED".equals(transferencia.getTipo())) {
            var resultado = regraTED.processar(transferencia);
            if (resultado.getMotivoRejeicao()!= null) {
                throw new IllegalArgumentException(resultado.getMotivoRejeicao());
            }
        }
    }
}
