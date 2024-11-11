package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Produto;
import br.edu.ifsc.fln.vendas.repository.ProdutoRepository;
import br.edu.ifsc.fln.vendas.service.CadastroProdutoService;
import br.edu.ifsc.fln.vendas.service.MovimentacaoDeEstoqueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController //Componente Spring com semântica de controlador
@RequestMapping("/produtos")
public class ProdutoController {

	private CadastroProdutoService cadastroProdutoService;
	private ProdutoRepository produtoRepository;
	private MovimentacaoDeEstoqueService movimentacaoDeEstoqueService;
	
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	//recurso para pesquisar produto por id
	@GetMapping("/{id}")
	public ResponseEntity<Produto> pesquisar(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public Produto inserir(@Valid @RequestBody Produto produto) {
		//produto = produtoRepository.save(produto);
		//vamos melhorar a instrução abaixo em outra oportunidade
		//Localização da categoria por meio do id da categoria
        // associado a produto.
		//Optional<Categoria> c = categoriaRepository.findById(produto.getCategoria().getId());
		//produto.setCategoria(c.get());
		//Optional<Fornecedor> f = fornecedorRepository.findById(produto.getFornecedor().getId());
		//produto.setFornecedor(f.get());
		produto = cadastroProdutoService.salvar(produto);
		return produto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Produto produto) {
		
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
//			Optional<Categoria> c = categoriaRepository.findById(entidadeAtualizada.getCategoria().getId());
//			entidadeAtualizada.setCategoria(c.get());
//			Optional<Fornecedor> f = fornecedorRepository.findById(entidadeAtualizada.getFornecedor().getId());
//			entidadeAtualizada.setFornecedor(f.get());
			produto.setId(id);
			Produto entidadeAtualizada = cadastroProdutoService.salvar(produto);
			return ResponseEntity.ok(entidadeAtualizada);
		}
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			cadastroProdutoService.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
	
    @PutMapping("/{id}/repor")
    public Produto reporEstoque(@PathVariable Integer id, @RequestParam int quantidade) throws Exception {
//        Produto produto = produtoRepository.findById(id)
//                .orElseThrow(() -> new Exception("Produto não encontrado"));
//        produto.getEstoque().repor(quantidade);
//        return produtoRepository.save(produto);
    	return movimentacaoDeEstoqueService.reporEstoque(id, quantidade);
    }

    @PutMapping("/{id}/retirar")
    public Produto retirarEstoque(@PathVariable Integer id, @RequestParam int quantidade) throws Exception {
//        Produto produto = produtoRepository.findById(id)
//                .orElseThrow(() -> new Exception("Produto não encontrado"));
//        produto.getEstoque().retirar(quantidade);
//        return produtoRepository.save(produto);
    	return movimentacaoDeEstoqueService.retirarEstoque(id, quantidade);
    }	
}
