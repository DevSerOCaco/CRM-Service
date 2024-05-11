package com.postech.orderservice.service.impl;

import com.postech.orderservice.entities.*;
import com.postech.orderservice.repositories.ClienteRepository;
import com.postech.orderservice.repositories.PedidoRepository;
import com.postech.orderservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        // Buscar o cliente no banco de dados
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getIdCliente())
                .orElseThrow(() ->
                        new IllegalArgumentException("Cliente não encontrado com ID: " + pedido.getCliente().getIdCliente()));

        //BUSCAR PRODUTO

        // Calcular o total do pedido

        // Criar o pedido

        // Salvar o pedido no banco de dados
        return null; //pedidoRepository.save(pedido);

    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedidoNovo) {
        Pedido pedido = this.buscarPedidoPorId(id);

        pedido.setIdPedido(id);
        pedido.setCliente(pedidoNovo.getCliente());
        pedido.setItens(pedidoNovo.getItens());
        pedido.setTotalPedido(pedidoNovo.getTotalPedido());

        return pedidoRepository.save(pedido);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}