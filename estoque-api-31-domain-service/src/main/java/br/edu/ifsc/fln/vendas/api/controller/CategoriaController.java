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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;
import br.edu.ifsc.fln.vendas.repository.CategoriaRepository;
import br.edu.ifsc.fln.vendas.service.CadastroCategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	private final CategoriaRepository categoriaRepository;
	private final CadastroCategoriaService cadastroCategoriaService;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> pesquisar(@PathVariable Integer id) {
		return categoriaRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> pesquisar(@PathVariable String descricao) {
		List<Categoria> categorias = categoriaRepository.findByDescricao(descricao);
		if (categorias.size() > 0) {
			return ResponseEntity.ok(categorias);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //devolve o status 201
	public Categoria inserir(@Valid @RequestBody Categoria categoria) {
		return cadastroCategoriaService.salvar(categoria);
		//return categoriaRepository.save(categoria);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Categoria categoria) {
		
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			categoria.setId(id);
			Categoria entidadeAtualizada = cadastroCategoriaService.salvar(categoria);
			return ResponseEntity.ok(entidadeAtualizada);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			cadastroCategoriaService.excluir(id);
			//categoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
	
}