package com.postech.orderservice.service;

import com.postech.orderservice.entities.Pedido;
import com.postech.orderservice.entities.PedidoRequest;
import com.postech.orderservice.entities.PedidoResponse;

import java.util.List;

public interface PedidoService {
    List<Pedido> listarPedidos();

    Pedido buscarPedidoPorId(Long id);

    PedidoResponse criarPedido(Long clienteId, PedidoRequest pedido);

    Pedido atualizarPedido(Long id, Pedido pedido);

    void deletarPedido(Long id);
}
