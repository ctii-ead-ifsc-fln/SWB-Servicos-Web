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

import br.edu.ifsc.fln.vendas.model.domain.Categoria;
import br.edu.ifsc.fln.vendas.repository.CategoriaRepository;
import br.edu.ifsc.fln.vendas.service.CadastroCategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

//@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	private final CategoriaRepository categoriaRepository;
	private final CadastroCategoriaService cadastroCategoriaService;
	
	@Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas.")
	    @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso",
	        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)))
	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@Operation(summary = "Buscar categoria por ID", description = "Retorna os detalhes de uma categoria com base no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> pesquisar(@PathVariable Integer id) {
		return categoriaRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Buscar categorias por descrição", description = "Retorna uma lista de categorias com base na descrição informada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))),
        @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada com a descrição informada")
    })
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> pesquisar(@PathVariable String descricao) {
		List<Categoria> categorias = categoriaRepository.findByDescricao(descricao);
		if (categorias.size() > 0) {
			return ResponseEntity.ok(categorias);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Inserir uma nova categoria", description = "Adiciona uma nova categoria ao sistema.")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)))
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED) //devolve o status 201
	public Categoria inserir(@Valid @RequestBody Categoria categoria) {
		return cadastroCategoriaService.salvar(categoria);
		//return categoriaRepository.save(categoria);
	}
	
	@Operation(summary = "Atualizar categoria existente", description = "Atualiza os dados de uma categoria existente com base no ID informado.")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))),
	        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
	    })
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
	
	@Operation(summary = "Remover categoria", description = "Exclui uma categoria com base no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
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
