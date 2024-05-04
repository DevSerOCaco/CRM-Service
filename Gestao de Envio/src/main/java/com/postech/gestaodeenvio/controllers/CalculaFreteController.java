package com.postech.gestaodeenvio.controllers;

import com.postech.gestaodeenvio.service.integracao.MelhorEnvioClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/frete")
public class CalculaFreteController {

    private final MelhorEnvioClient melhorEnvioClient;

    public CalculaFreteController(MelhorEnvioClient melhorEnvioClient) {
        this.melhorEnvioClient = melhorEnvioClient;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> calculaFrete(@PathVariable String cep) throws IOException, InterruptedException {
        return melhorEnvioClient.calcularFrete(cep);
    }
}
