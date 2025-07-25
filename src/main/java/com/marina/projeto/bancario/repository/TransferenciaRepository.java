package com.marina.projeto.bancario.repository;

import com.marina.projeto.bancario.model.Transferencia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransferenciaRepository extends MongoRepository<Transferencia, String> {

}
