package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Produto;
import br.edu.ifsc.fln.vendas.repository.ProdutoRepository;

@RestController //Componente Spring com semântica de controlador
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	//Construtor para iniciar o repository - injeção substiuindo autowired
	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@GetMapping("/produtos")
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> pesquisar(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
}
