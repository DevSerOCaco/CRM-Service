package br.com.fiap.gestaoprodutos.controllers;

import br.com.fiap.gestaoprodutos.entities.Produto;
import br.com.fiap.gestaoprodutos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Gestão de Produtos", description = "Controller para manutenção na Gestão de Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listarProdutos")
    @Operation(summary = "Lista todos os Produtos", method = "GET")
    public ResponseEntity<Page<Produto>> findAll(@PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable) {
        Page<Produto> produtos = produtoService.listaProdutos(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/getProduto/{id}")
    @Operation(summary = "Busca Produtos pelo id", method = "GET")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/cadastroProduto")
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        Produto savedProduto = produtoService.salvarProduto(produto);
        return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
    }

    @PutMapping("/atualizaProduto/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        Produto updatedproduto = produtoService.updateProduto(id, produto);
        return ResponseEntity.ok(updatedproduto);
    }

    @DeleteMapping("/deleteProduto/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

}
