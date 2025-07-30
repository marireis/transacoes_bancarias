package com.marina.projeto.bancario.controller;

import com.marina.projeto.bancario.model.Transferencia;
import com.marina.projeto.bancario.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/buscar")
    public List<Transferencia> buscarPorDataEValor(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data,
            @RequestParam("valor") double valor) {
        return service.buscarPorDataEValor(data, valor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
