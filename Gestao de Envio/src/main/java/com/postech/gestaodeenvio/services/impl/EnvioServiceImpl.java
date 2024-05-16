package com.postech.gestaodeenvio.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.gestaodeenvio.entities.Envio;
import com.postech.gestaodeenvio.repository.EnvioRepository;
import com.postech.gestaodeenvio.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnvioServiceImpl implements EnvioService {
    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Envio criarEnvio(Envio envio) {
        Boolean pedidoExistente = restTemplate.getForObject("http://localhost:8089/pedidos/{pedidoId}",
                Boolean.class, envio.getPedidoId());

        if (pedidoExistente != null && pedidoExistente) {
            // Se o pedido existe, salva o envio
            return envioRepository.save(envio);
        } else {
            throw new IllegalArgumentException("O pedido associado não existe.");
        }
    }
}
