package com.postech.gestaodeenvio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.gestaodeenvio.entities.Envio;
import com.postech.gestaodeenvio.repository.EnvioRepository;
import com.postech.gestaodeenvio.services.impl.EnvioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnvioServiceImplTest {

    @InjectMocks
    private EnvioServiceImpl envioService;

    @Mock
    private EnvioRepository envioRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarEnvioComPedidoExistente() {
        Envio envio = new Envio();
        envio.setPedidoId(1L);

        when(restTemplate.getForObject("http://localhost:8089/pedidos/{pedidoId}",
                Boolean.class, envio.getPedidoId())).thenReturn(true);

        when(envioRepository.save(any(Envio.class))).thenReturn(envio);

        Envio resultado = envioService.criarEnvio(envio);

        assertNotNull(resultado);
        assertEquals(envio.getPedidoId(), resultado.getPedidoId());
        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    public void testCriarEnvioComPedidoNaoExistente() {
        Envio envio = new Envio();
        envio.setPedidoId(1L);

        when(restTemplate.getForObject("http://localhost:8089/pedidos/{pedidoId}",
                Boolean.class, envio.getPedidoId())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            envioService.criarEnvio(envio);
        });

        assertEquals("O pedido associado não existe.", exception.getMessage());
        verify(envioRepository, never()).save(any(Envio.class));
    }
}
