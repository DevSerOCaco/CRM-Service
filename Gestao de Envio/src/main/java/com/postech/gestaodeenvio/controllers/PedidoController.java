package com.postech.gestaodeenvio.controllers;

import com.postech.gestaodeenvio.entities.Pedido;
import com.postech.gestaodeenvio.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/importar")
    public ResponseEntity<Pedido> importarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.importarPedido(pedido));
    }
}
