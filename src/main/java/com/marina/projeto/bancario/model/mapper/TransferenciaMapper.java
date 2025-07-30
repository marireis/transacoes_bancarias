package com.marina.projeto.bancario.model.mapper;

import com.marina.projeto.bancario.model.Transferencia;
import com.marina.projeto.bancario.model.dto.TransferenciaDTO;

import java.time.LocalDateTime;

public class TransferenciaMapper {

    public static Transferencia toEntity(TransferenciaDTO dto) {
        Transferencia transferencia = new Transferencia();
        transferencia.setTipo(dto.getTipo());
        transferencia.setValor(dto.getValor());
        transferencia.setOrigem(dto.getOrigem());
        transferencia.setDestino(dto.getDestino());
        transferencia.setTipoConta(dto.getTipoConta());
        transferencia.setDataHora(LocalDateTime.now());
        return transferencia;
    }
}
