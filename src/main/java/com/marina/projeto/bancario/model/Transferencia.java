package com.marina.projeto.bancario.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "transferencias")
public class Transferencia {

    @Id
    private String id;
    private String tipo;
    private double valor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();
    private String origem;
    private String destino;
    private String tipoConta;
}
