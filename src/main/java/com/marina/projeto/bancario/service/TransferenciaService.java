package com.marina.projeto.bancario.service;

import com.marina.projeto.bancario.model.ResultadoTransferencia;
import com.marina.projeto.bancario.model.Transferencia;
import com.marina.projeto.bancario.repository.TransferenciaRepository;
import com.marina.projeto.bancario.strategy.RegraTED;
import com.marina.projeto.bancario.strategy.RegraTransferencia;
import com.marina.projeto.bancario.strategy.RegraTransferenciaFactory;
import com.marina.projeto.bancario.validator.TransferenciaValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private TransferenciaValidator validator;
    @Autowired
    private RegraTED ted;

    public Transferencia salvar(Transferencia transferencia) {

        transferencia.setDataHora(LocalDateTime.now());
        log.info("Iniciando processamento da transferência: {}", transferencia);

        RegraTransferencia regra = RegraTransferenciaFactory.getRegra(transferencia.getTipo());
        ResultadoTransferencia resultado = regra.processar(transferencia);

        if (!resultado.isSucesso()) {
            log.warn("Transferência rejeitada: {}", resultado.getMotivoRejeicao());
            throw new IllegalArgumentException("Transferência rejeitada: " + resultado.getMotivoRejeicao());
        }
        log.info("Transferência aceita. Salvando no banco.");
        return repository.save(transferencia);
    }

    public List<Transferencia> listarTodas() {
        return repository.findAll();
    }

    public List<Transferencia> buscarPorDataEValor(LocalDateTime data, double valor) {
        log.info("Buscando transferências com data = {} e valor = {}", data, valor);
        return repository.findByDataHoraAndValor(data, valor);

    }

    public void deletar(String id) {

        log.info("Deletando transferência com ID: {}", id);
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Transferência não encontrada com ID: " + id);
        }
        repository.deleteById(id);

    }
}
