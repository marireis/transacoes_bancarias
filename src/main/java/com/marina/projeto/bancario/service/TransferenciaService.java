package com.marina.projeto.bancario.service;

import com.marina.projeto.bancario.model.Transferencia;
import com.marina.projeto.bancario.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    public Transferencia salvar(Transferencia transferencia) {
        return repository.save(transferencia);
    }

    public List<Transferencia> listarTodas() {
        return repository.findAll();
    }
}
