package com.postech.gestaodeenvio.services.impl;

import com.postech.gestaodeenvio.entities.Itens;
import com.postech.gestaodeenvio.entities.Pedido;
import com.postech.gestaodeenvio.entities.bodys.Options;
import com.postech.gestaodeenvio.entities.bodys.Volume;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.FromInserirFrete;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.InserirFretesRequest;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.ProductsInserirFretes;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.ToInserirFrete;
import com.postech.gestaodeenvio.services.PedidoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {


    @Override
    public Pedido importarPedido(Pedido pedido) {
        InserirFretesRequest request = montarRequest(pedido);
        System.out.println("Importando pedido: " + pedido);
        //REQUEST PRONTA BASTA IMPLEMENTAR
        return null;
    }

    private InserirFretesRequest montarRequest(Pedido pedido) {

        ToInserirFrete to = new ToInserirFrete(pedido.getEndereco().getCep(),
                pedido.getCliente().getNome(),
                pedido.getEndereco().getLogradouro(),
                pedido.getEndereco().getCidade(),
                pedido.getCliente().getCpf());

        FromInserirFrete from = new FromInserirFrete();

        List<ProductsInserirFretes> produtos = new ArrayList<>();
        List<Volume> volumes = new ArrayList<>();
        for (Itens item : pedido.getItens()) {
            ProductsInserirFretes prodctItem = new ProductsInserirFretes(item.getId(),
                    item.getNome(), item.getQuantidade(),item.getPrecoUnitario());
            produtos.add(prodctItem);

            //cada item vai gerar um volume
            Volume volume = new Volume();
            volumes.add(volume);
        }
        Options options = new Options();
        return new InserirFretesRequest(from, to, produtos, volumes, options);
    }
}
