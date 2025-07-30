package com.marina.projeto.bancario.repository;

import com.marina.projeto.bancario.model.Transferencia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferenciaRepository extends MongoRepository<Transferencia, String> {

    List<Transferencia> findByDataHoraAndValor(LocalDateTime data, double valor);
}
