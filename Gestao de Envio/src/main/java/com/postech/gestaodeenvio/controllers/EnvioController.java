package com.postech.gestaodeenvio.controllers;

import com.postech.gestaodeenvio.entities.Envio;
import com.postech.gestaodeenvio.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping
    public ResponseEntity<?> envio(@RequestBody Envio envio) {

        try {
            Envio novoEnvio = envioService.criarEnvio(envio);
            return new ResponseEntity<>(novoEnvio, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Pedido nao existe ou ja foi postado.", HttpStatus.BAD_REQUEST);
        }
    }
}
