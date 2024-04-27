package br.com.fiap.gestaoprodutos.service.impl;

import br.com.fiap.gestaoprodutos.entities.Produto;
import br.com.fiap.gestaoprodutos.reporitories.ProdutoRepository;
import br.com.fiap.gestaoprodutos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Page<Produto> listaProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public Produto getProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado para o ID: " + id));
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getDescricao() == null || produto.getPreco() == null)
            throw new IllegalArgumentException("Preencha corretamente os dados de Produto");

        if (produto.getQuantidadeEstoque() == null)
            produto.setQuantidadeEstoque(0);

        return produtoRepository.save(produto);
    }

    @Override
    public Produto updateProduto(Long id, Produto produtoNovo) {
        Produto produto = this.getProdutoPorId(id);
        System.out.println(produtoNovo.getNome() + " / "+ produtoNovo.getDescricao()
                +" / "+ produtoNovo.getPreco()  + " / "+ produtoNovo.getQuantidadeEstoque() );

        if (produtoNovo.getNome() == null || produtoNovo.getDescricao() == null
                || produtoNovo.getPreco() == null || produtoNovo.getQuantidadeEstoque() == null)
            throw new IllegalArgumentException("Preencha corretamente os dados que devem ser atualizados");

        produto.setNome(produtoNovo.getNome());
        produto.setDescricao(produtoNovo.getDescricao());
        produto.setPreco(produtoNovo.getPreco());
        produto.setQuantidadeEstoque(produtoNovo.getQuantidadeEstoque());

        return produtoRepository.save(produto);
    }

    @Override
    public void deleteProduto(Long id) {
        Produto produto = this.getProdutoPorId(id);

        produtoRepository.delete(produto);

    }

}
