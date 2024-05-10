package com.postech.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private long id;
    private Cliente cliente;
    private List<Produto> itens;
    private double totalPedido;
}
