package br.edu.ifsc.fln.vendas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;
import br.edu.ifsc.fln.vendas.model.domain.Fornecedor;
import br.edu.ifsc.fln.vendas.model.domain.Produto;
import br.edu.ifsc.fln.vendas.repository.CategoriaRepository;
import br.edu.ifsc.fln.vendas.repository.FornecedorRepository;
import br.edu.ifsc.fln.vendas.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroProdutoService {
	
	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;
	private final FornecedorRepository fornecedorRepository;
	
	@Transactional //IMPORTANTE: 
	               //import org.springframework.transaction.annotation.Transactional
	public Produto salvar(Produto produto) {
		produto = produtoRepository.save(produto);
		Optional<Categoria> c = categoriaRepository.findById(produto.getCategoria().getId());
		produto.setCategoria(c.get());
		Optional<Fornecedor> f = fornecedorRepository.findById(produto.getFornecedor().getId());
		produto.setFornecedor(f.get());				
		return produto;
	}
	
	@Transactional
	public void excluir(Integer id) {
		produtoRepository.deleteById(id);
	}
}
