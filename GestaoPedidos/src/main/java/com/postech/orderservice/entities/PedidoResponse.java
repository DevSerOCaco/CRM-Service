package com.postech.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoResponse {
    private Cliente cliente;
    private List<Produto> itens;
    private String totalPedido;
}
