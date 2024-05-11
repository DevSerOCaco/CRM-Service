package com.postech.orderservice.controllers;

import com.postech.orderservice.entities.Pedido;
import com.postech.orderservice.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
@Tag(name = "Gestão de Pedidos", description = "Controller para manutenção na Gestão de Pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/listarPedidos", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista todos os Pedidos", method = "GET")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return new ResponseEntity<>(pedidoService.listarPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um pedido pelo Id", method = "GET")
    public Pedido buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido pedidoResponse = pedidoService.criarPedido(pedido);
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
