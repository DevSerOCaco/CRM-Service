package com.postech.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.orderservice.repositories.ClienteRepository;
import com.postech.orderservice.repositories.PedidoRepository;
import com.postech.orderservice.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;


public class PedidoServiceTest {
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;

    AutoCloseable openMocks;


    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoService = new PedidoServiceImpl(pedidoRepository, clienteRepository, restTemplate, objectMapper);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

/*
    @Nested
    class RegistrarPedido {
        @Test
        void devePermitirRegistrarPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();

            when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

            var pedidoReg = pedidoService.criarPedido(pedido, 1L);

            assertThat(pedidoReg).isInstanceOf(Pedido.class).isNotNull();
            assertThat(pedidoReg.getIdPedido()).isEqualTo(pedido.getIdPedido());
            assertThat(pedidoReg.getTotalPedido()).isNotNull();
        }
    }


 */

}
