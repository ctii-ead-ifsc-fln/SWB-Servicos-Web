package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Fornecedor;
import br.edu.ifsc.fln.vendas.repository.FornecedorRepository;
import br.edu.ifsc.fln.vendas.service.CadastroFornecedorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")  // Especifique a origem do frontend
@AllArgsConstructor
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	private FornecedorRepository fornecedorRepository;
	private CadastroFornecedorService cadastroFornecedorService;
	
	@GetMapping
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> pesquisar(@PathVariable Integer id) {
		return fornecedorRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public Fornecedor inserir(@Valid @RequestBody Fornecedor fornecedor) {
//		boolean haEmail = fornecedorRepository.findByEmail(fornecedor.getEmail())
//				.isPresent();
//		if (haEmail) {
//			throw new NegocioEstoqueException("Este email já existe e não pode ser utilizado!");
//		}
		fornecedor = cadastroFornecedorService.salvar(fornecedor);
		return fornecedor;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Fornecedor fornecedor) {
//		boolean haEmail = fornecedorRepository.findByEmail(fornecedor.getEmail())
//				.filter(f -> !f.equals(fornecedor))
//				.isPresent(); 
//		if (haEmail) {
//			throw new NegocioEstoqueException("Este email já existe e não pode ser utilizado!");
//		}
		if (!fornecedorRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			fornecedor.setId(id);
			Fornecedor entidadeAtualizada = cadastroFornecedorService.salvar(fornecedor);
			return ResponseEntity.ok(entidadeAtualizada);
		}
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!fornecedorRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			cadastroFornecedorService.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}	
}
