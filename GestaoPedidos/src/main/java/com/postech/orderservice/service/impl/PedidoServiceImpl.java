package com.postech.orderservice.service.impl;

import com.postech.orderservice.entities.*;
import com.postech.orderservice.repositories.ClienteRepository;
import com.postech.orderservice.repositories.PedidoRepository;
import com.postech.orderservice.repositories.ProdutoRepository;
import com.postech.orderservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
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
    public PedidoResponse criarPedido(Long clienteId, PedidoRequest pedidoRequest) {
        // Buscar o cliente no banco de dados
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + clienteId));

        // Verificar se os produtos existem no banco de dados e criar uma lista de produtos correspondentes
        List<Produto> produtos = pedidoRequest.getItens().stream()
                .map(produtoRequest -> {
                    Produto produto = produtoRepository.findById(produtoRequest.getProdutoId())
                            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + produtoRequest.getProdutoId()));
                    produto.setQuantidade(produtoRequest.getQuantidade()); // Definir a quantidade do produto
                    return produto;
                })
                .collect(Collectors.toList());

        // Calcular o total do pedido
        double totalPedido = produtos.stream()
                .mapToDouble(produto -> produto.getPrecoUnitario() * produto.getQuantidade())
                .sum();

        // Criar o pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(produtos);
        pedido.setTotalPedido(totalPedido);

        // Salvar o pedido no banco de dados
        pedidoRepository.save(pedido);

        // Construir e retornar o PedidoResponse
        return new PedidoResponse(cliente, produtos, String.format("R$ %.2f", totalPedido));
    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedido) {
        if (!pedidoRepository.existsById(id)) {
            return null;
        }
        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}