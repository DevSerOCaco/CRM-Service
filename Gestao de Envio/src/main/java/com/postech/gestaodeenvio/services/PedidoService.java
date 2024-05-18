package com.postech.gestaodeenvio.services;

import com.postech.gestaodeenvio.entities.Pedido;
import org.springframework.stereotype.Service;

//ESSE SERVICE NAO IRA PERSISTIR O PEDIDO, apenas comunicar com a API de envio.
@Service
public interface PedidoService {

    public Pedido importarPedido(Pedido pedido);
}
