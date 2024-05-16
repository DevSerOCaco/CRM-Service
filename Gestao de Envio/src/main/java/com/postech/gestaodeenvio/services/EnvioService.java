package com.postech.gestaodeenvio.services;

import com.postech.gestaodeenvio.entities.Envio;
import org.springframework.stereotype.Service;

@Service
public interface EnvioService {

    Envio criarEnvio(Envio envio);


}
