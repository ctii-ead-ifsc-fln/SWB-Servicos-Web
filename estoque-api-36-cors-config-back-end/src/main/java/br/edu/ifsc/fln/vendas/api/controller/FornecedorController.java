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

import br.edu.ifsc.fln.vendas.model.domain.Fornecedor;
import br.edu.ifsc.fln.vendas.repository.FornecedorRepository;
import br.edu.ifsc.fln.vendas.service.CadastroFornecedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	private FornecedorRepository fornecedorRepository;
	private CadastroFornecedorService cadastroFornecedorService;
	
	@Operation(summary = "Listar todos os fornecedores", description = "Retorna uma lista de todos os fornecedores cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class)))
    @GetMapping
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}
	
	@Operation(summary = "Buscar fornecedor por ID", description = "Retorna os detalhes de um fornecedor com base no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fornecedor encontrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class))),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> pesquisar(@PathVariable Integer id) {
		return fornecedorRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Inserir um novo fornecedor", description = "Adiciona um novo fornecedor ao sistema.")
    @ApiResponse(responseCode = "201", description = "Fornecedor criado com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class)))
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public Fornecedor inserir(@Valid @RequestBody Fornecedor fornecedor) {
		fornecedor = cadastroFornecedorService.salvar(fornecedor);
		return fornecedor;
	}
	
	@Operation(summary = "Atualizar fornecedor existente", description = "Modifica os dados de um fornecedor existente com base no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Fornecedor.class))),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Fornecedor fornecedor) {
		if (!fornecedorRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			fornecedor.setId(id);
			Fornecedor entidadeAtualizada = cadastroFornecedorService.salvar(fornecedor);
			return ResponseEntity.ok(entidadeAtualizada);
		}
	}	
	
	@Operation(summary = "Remover fornecedor", description = "Exclui um fornecedor com base no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Fornecedor excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
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
