package com.postech.gestaodeenvio.services.integracao;

import com.postech.gestaodeenvio.services.EnvioService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EnvioConsumer {

        private final EnvioService envioService;

        public EnvioConsumer(EnvioService envioService) {
            this.envioService = envioService;
        }

        @Bean(name = "teste")
        Consumer<EnvioRequest> consumer() {
            return envioRequest -> {
                System.out.println("Envio recebido: " + envioRequest);
            };
        }
}
