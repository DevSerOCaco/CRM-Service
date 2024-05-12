package com.postech.orderservice.service.impl;

import com.postech.orderservice.entities.*;
import com.postech.orderservice.repositories.ClienteRepository;
import com.postech.orderservice.repositories.PedidoRepository;
import com.postech.orderservice.repositories.ProdutoRepository;
import com.postech.orderservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
    public Pedido criarPedido(Pedido pedido, Long idCliente) {
        Cliente cliente = getClienteById(idCliente);
        pedido.setCliente(cliente);

        BigDecimal totalPedido = calcularTotalPedido(pedido.getItens());
        pedido.setTotalPedido(totalPedido);

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedidoNovo) {
        Pedido pedidoExistente = buscarPedidoPorId(id);

        pedidoExistente.setCliente(pedidoNovo.getCliente());
        pedidoExistente.setItens(pedidoNovo.getItens());
        pedidoExistente.setTotalPedido(calcularTotalPedido(pedidoNovo.getItens()));

        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void deletarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Pedido não encontrado com ID: " + id);
        }
    }

    private Cliente getClienteById(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + idCliente));
    }

    private BigDecimal calcularTotalPedido(List<ItemPedido> itensPedido) {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido itemPedido : itensPedido) {
            Produto produto = getProdutoById(itemPedido.getIdProduto());
            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
            total = total.add(subtotal);
        }
        return total;
    }

    private Produto getProdutoById(Long idProduto) {
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + idProduto));
    }

}