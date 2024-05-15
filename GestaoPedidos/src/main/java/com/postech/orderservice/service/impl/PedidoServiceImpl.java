package com.postech.orderservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.orderservice.dto.ProdutoDto;
import com.postech.orderservice.entities.*;
import com.postech.orderservice.repositories.ClienteRepository;
import com.postech.orderservice.repositories.PedidoRepository;
import com.postech.orderservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, RestTemplate restTemplate,
                             ObjectMapper objectMapper) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
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
//        Cliente cliente = getClienteById(idCliente);
        Cliente cliente = new Cliente(1l,"nome","111111111","a@a.com","rua x");
        pedido.setCliente(cliente);

        //recupera produtos
        List<ProdutoDto> produtos = this.recuperaProdutos(pedido.getItens());

        //verificação de estoque chamando gestão de produtos
        this.verificaEstoque(produtos, pedido.getItens());

        //total do pedido
        BigDecimal totalPedido = calcularTotalPedido(pedido.getItens(), produtos);
        pedido.setTotalPedido(totalPedido);

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedidoNovo) {
        Pedido pedidoExistente = buscarPedidoPorId(id);

        pedidoExistente.setCliente(pedidoNovo.getCliente());
        pedidoExistente.setItens(pedidoNovo.getItens());
//        pedidoExistente.setTotalPedido(calcularTotalPedido(pedidoNovo.getItens()));

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

    private List<ProdutoDto> recuperaProdutos(List<ItemPedido> itensPedido) {
        List<ProdutoDto> produtos = new ArrayList<>();

        if (itensPedido == null) {
            throw new RuntimeException("Pedido deve ter algum item");
        }

        for (ItemPedido itemPedido : itensPedido) {
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://localhost:8080/produtos/getProduto/{id}",
                    String.class,
                    itemPedido.getIdProduto()
            );

            ProdutoDto produto = new ProdutoDto();

            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NoSuchElementException("Produto não encontrado");
            } else {
                try {
                    JsonNode produtoJson = objectMapper.readTree(response.getBody());
                    produto.setId(produtoJson.get("id").asLong());
                    produto.setNome(produtoJson.get("nome").asText());
                    produto.setDescricao(produtoJson.get("descricao").asText());
                    produto.setQuantidadeEstoque(produtoJson.get("quantidadeEstoque").asInt());
                    produto.setPreco(produtoJson.get("preco").decimalValue());

                } catch (IOException e) {
                    throw new RuntimeException("não foi possível fazer a conexão com o app de produtos");
                }
            }

            produtos.add(produto);
        }
        return produtos;
    }

    private void verificaEstoque(List<ProdutoDto> produtos, List<ItemPedido> itens) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getQuantidadeEstoque() < itens.get(i).getQuantidade()) {
                throw new IllegalArgumentException("O item " + produtos.get(i).getNome()
                        + " não tem " + itens.get(i).getQuantidade() + " no estoque." +
                        " Total: " + produtos.get(i).getQuantidadeEstoque());
            }
        }

        for (int i = 0; i < produtos.size(); i++) {
            this.removeEstoque(produtos.get(i).getId(), itens.get(i).getQuantidade());
        }
    }

    private void removeEstoque(Long id, Integer quantidade) {
        try {
            String url = "http://localhost:8080/removeEstoque/"+id+"/"+quantidade;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);


//            restTemplate.put(
//                    "http://localhost:8080/removeEstoque/{id}/{quantidade}",
//                    String.class,
//                    id,
//                    quantidade
//            );
        } catch (Exception e) {
            throw new RuntimeException("não foi possível remover do estoque");
        }
    }

    private BigDecimal calcularTotalPedido(List<ItemPedido> itens, List<ProdutoDto> produtos) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < produtos.size(); i++) {
            total = total.add(
                    produtos.get(i).getPreco().multiply(
                            BigDecimal.valueOf(itens.get(i).getQuantidade())
                    )
            );
        }
        return total;
    }

}