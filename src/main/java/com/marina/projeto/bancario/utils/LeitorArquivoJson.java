package com.marina.projeto.bancario.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marina.projeto.bancario.model.Transferencia;

import java.io.InputStream;
import java.util.List;

public class LeitorArquivoJson {
    public static List<Transferencia> lerTransferencias(String caminho) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        InputStream is = LeitorArquivoJson.class.getResourceAsStream(caminho);
        return mapper.readValue(is, new TypeReference<List<Transferencia>>() {});
    }
}