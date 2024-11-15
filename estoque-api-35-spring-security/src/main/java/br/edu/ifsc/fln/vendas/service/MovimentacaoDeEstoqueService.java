package br.edu.ifsc.fln.vendas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsc.fln.vendas.model.domain.Produto;
import br.edu.ifsc.fln.vendas.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovimentacaoDeEstoqueService {
	private final ProdutoRepository produtoRepository;
	
	public Produto buscarProdutoPorId(Integer id) throws Exception {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    @Transactional
    public Produto reporEstoque(Integer produtoId, int quantidade) throws Exception {
        Produto produto = buscarProdutoPorId(produtoId);
        produto.getEstoque().repor(quantidade); // Lógica na classe Estoque
        return produtoRepository.save(produto); // Salva as mudanças
    }

    @Transactional
    public Produto retirarEstoque(Integer produtoId, int quantidade) throws Exception {
        Produto produto = buscarProdutoPorId(produtoId);
        produto.getEstoque().retirar(quantidade); // Lógica na classe Estoque
        return produtoRepository.save(produto); // Salva as mudanças
    }	
}
