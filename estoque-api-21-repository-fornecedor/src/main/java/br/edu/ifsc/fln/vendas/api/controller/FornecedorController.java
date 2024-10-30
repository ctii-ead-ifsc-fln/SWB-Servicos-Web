package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Fornecedor;
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
}
