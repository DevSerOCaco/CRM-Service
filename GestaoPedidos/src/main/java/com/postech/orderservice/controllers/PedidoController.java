package com.postech.orderservice.controllers;

import com.postech.orderservice.entities.Pedido;
import com.postech.orderservice.entities.PedidoRequest;
import com.postech.orderservice.entities.PedidoResponse;
import com.postech.orderservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest pedidoRequest,
                                                      @RequestParam Long clienteId) {
        PedidoResponse pedidoResponse = pedidoService.criarPedido(clienteId, pedidoRequest);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Pedido atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.atualizarPedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }
}