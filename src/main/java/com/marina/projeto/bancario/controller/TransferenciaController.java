package com.marina.projeto.bancario.controller;

import com.marina.projeto.bancario.model.Transferencia;
import com.marina.projeto.bancario.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    @PostMapping("/transferir")
    public Transferencia salvar(@RequestBody Transferencia transferencia) {
        return service.salvar(transferencia);
    }

    @GetMapping
    public List<Transferencia> listarTodas() {
        return service.listarTodas();
    }

}
