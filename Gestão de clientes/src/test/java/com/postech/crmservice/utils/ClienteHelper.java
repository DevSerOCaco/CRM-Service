package com.postech.crmservice.utils;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.Endereco;

import java.util.ArrayList;
import java.util.List;

public abstract class ClienteHelper {

    public static Cliente gerarRegistro() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente teste");
        cliente.setEmail("teste@email.com");
        cliente.setCpf("111.111.111-11");
        cliente.setTelefone("(11) 97777-7777");
        cliente.setAtivo(true);

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Rua da Silva");
        endereco.setNumero("911");
        endereco.setComplemento("Silva");
        endereco.setCidade("Silva");
        endereco.setBairro("Silva");
        endereco.setUf("SP");
        endereco.setCep("00000-000");
        endereco.setCliente(cliente);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        cliente.setEnderecos(enderecos);

        return cliente;
    }
}
