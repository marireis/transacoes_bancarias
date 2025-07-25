package com.marina.projeto.bancario.strategy;

import com.marina.projeto.bancario.model.ResultadoTransferencia;
import com.marina.projeto.bancario.model.Transferencia;

public interface RegraTransferencia {


    ResultadoTransferencia processar(Transferencia transferencia);
}
