package com.marina.projeto.bancario.strategy;

public class RegraTransferenciaFactory {

    public static RegraTransferencia getRegra(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "PIX" -> new RegraPIX();
            case "DOC" -> new RegraDOC();
            case "TED" -> new RegraTED();
            default -> throw new IllegalArgumentException("Tipo de transferência inválido: " + tipo);
        };
    }
}
