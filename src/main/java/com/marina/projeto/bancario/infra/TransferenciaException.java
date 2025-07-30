package com.marina.projeto.bancario.infra;

public class TransferenciaException extends RuntimeException{

    public TransferenciaException(String message){
        super(message);
    }
}
