package com.postech.crmservice.service;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.mappers.ClienteMapper;
import com.postech.crmservice.repositories.ClienteRepository;
import com.postech.crmservice.services.ClienteService;
import com.postech.crmservice.services.ClienteServiceImpl;
import com.postech.crmservice.utils.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {

        openMocks = MockitoAnnotations.openMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository, clienteMapper);
    }

    @AfterEach
    void tearDow() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarCliente {
        @Test
        void deveRegistrarCliente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = clienteMapper.toDto(cliente);

            when(clienteRepository.save(cliente))
                    .thenAnswer(i -> i.getArgument(0));

            var clienteRegistrado = clienteService.save(clienteDto);
            assertThat(clienteRegistrado).isInstanceOf(ClienteDto.class).isNotNull();
            assertThat(clienteRegistrado.nome()).isEqualTo(cliente.getNome());
            assertThat(clienteRegistrado.email()).isEqualTo(cliente.getEmail());
            assertThat(clienteRegistrado.telefone()).isEqualTo(cliente.getTelefone());
            verify(clienteRepository, times(1)).save(any(Cliente.class));
        }
    }
}
