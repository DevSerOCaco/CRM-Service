package com.postech.gestaodeenvio.entities;

import lombok.Data;

import java.util.List;
@Data
public class Pedido {

    private long id;

    private StatusPedido status;

    private List<Itens> itens;

    private Endereco endereco;

    private Destinatario cliente;

    public Pedido(long id, List<Itens> itens, Endereco endereco, Destinatario cliente) {
        this.id = id;
        this.status = StatusPedido.PRONTO_PARA_ENVIO;
        this.itens = itens;
        this.endereco = endereco;
        this.cliente = cliente;
    }
}
