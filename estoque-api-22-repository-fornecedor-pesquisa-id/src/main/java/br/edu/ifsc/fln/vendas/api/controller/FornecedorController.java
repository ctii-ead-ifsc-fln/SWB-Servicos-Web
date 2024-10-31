package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Fornecedor;
import br.edu.ifsc.fln.vendas.model.domain.Produto;
import br.edu.ifsc.fln.vendas.repository.FornecedorRepository;

@RestController
public class FornecedorController {
	private FornecedorRepository fornecedorRepository;
	
	public FornecedorController(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	
	@GetMapping("/fornecedores")
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}
	
	@GetMapping("/fornecedores/{id}")
	public ResponseEntity<Fornecedor> pesquisar(@PathVariable Integer id) {
		return fornecedorRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
}
