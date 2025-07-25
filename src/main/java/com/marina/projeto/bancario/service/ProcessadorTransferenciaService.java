package com.marina.projeto.bancario.service;

import com.marina.projeto.bancario.model.ResultadoTransferencia;
import com.marina.projeto.bancario.model.Transferencia;
import com.marina.projeto.bancario.repository.TransferenciaRepository;
import com.marina.projeto.bancario.strategy.RegraTransferencia;
import com.marina.projeto.bancario.strategy.RegraTransferenciaFactory;
import com.marina.projeto.bancario.utils.LeitorArquivoJson;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProcessadorTransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    @PostConstruct
    public void carregarTransferencias() {
        try {
            List<Transferencia> transferencias = LeitorArquivoJson.lerTransferencias("/transferencias.json");
            for (Transferencia t : transferencias) {
                RegraTransferencia regra = RegraTransferenciaFactory.getRegra(t.getTipo());
                ResultadoTransferencia resultado = regra.processar(t);
                if (resultado.isSucesso()) {
                    repository.save(t);
                }
            }
            System.out.println("Transferências carregadas com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


