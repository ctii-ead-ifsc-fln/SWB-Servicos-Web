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
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<Categoria> pesquisar(@PathVariable Integer id) {
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
	
	@PostMapping("/categorias")
	@ResponseStatus(HttpStatus.CREATED) //devolve o status 201
	public Categoria inserir(@RequestBody Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Integer id,
											   @RequestBody Categoria categoria) {
		
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			categoria.setId(id);
			Categoria entidadeAtualizada = categoriaRepository.save(categoria);
			return ResponseEntity.ok(entidadeAtualizada);
		}
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			categoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
	
}
