package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;
import br.edu.ifsc.fln.vendas.repository.CategoriaRepository;

@RestController
public class CategoriaController {
	private final CategoriaRepository categoriaRepository;
	
	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@GetMapping("/categorias")
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	//usando ResponseEntity para retorna o código de resposta da requisição HTTP
	// com o corpo da mensagem.
	@GetMapping("/categorias/{id}")
	public ResponseEntity<Categoria> pesquisar(@PathVariable Integer id) {
//		Optional<Categoria> categoria = categoriaRepository.findById(id);
//		if (categoria.isPresent()) {
//			return ResponseEntity.ok(categoria.get());
//		}
//		
//		return ResponseEntity.notFound().build();
		//otimizando o retorno com method reference (poderia ser utilizado lambda também)
		return categoriaRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categorias/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> pesquisar(@PathVariable String descricao) {
		List<Categoria> categorias = categoriaRepository.findByDescricao(descricao);
		if (categorias.size() > 0) {
			return ResponseEntity.ok(categorias);
		}
		
		return ResponseEntity.notFound().build();
	}
}
