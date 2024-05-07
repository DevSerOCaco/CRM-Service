package com.postech.gestaodeenvio.controllers;

import com.postech.gestaodeenvio.service.integracao.MelhorEnvioClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/frete")
public class MelhorEnvioController {

    private final MelhorEnvioClient melhorEnvioClient;

    public MelhorEnvioController(MelhorEnvioClient melhorEnvioClient) {
        this.melhorEnvioClient = melhorEnvioClient;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> calculaFrete(@PathVariable String cep) throws IOException, InterruptedException {
        return melhorEnvioClient.calcularFrete(cep);
    }

    @GetMapping("/inserir")
    public ResponseEntity<?> inserirFrete() throws IOException, InterruptedException {
        return melhorEnvioClient.inserirFrete();
    }
}