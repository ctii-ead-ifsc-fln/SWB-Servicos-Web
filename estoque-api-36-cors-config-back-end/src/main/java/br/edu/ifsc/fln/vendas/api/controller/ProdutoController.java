package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import br.edu.ifsc.fln.vendas.dto.ProdutoDTO;
import br.edu.ifsc.fln.vendas.model.domain.Produto;
import br.edu.ifsc.fln.vendas.repository.ProdutoRepository;
import br.edu.ifsc.fln.vendas.service.CadastroProdutoService;
import br.edu.ifsc.fln.vendas.service.MovimentacaoDeEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController //Componente Spring com semântica de controlador
@RequestMapping("/produtos")
public class ProdutoController {

	private final CadastroProdutoService cadastroProdutoService;
	private final ProdutoRepository produtoRepository;
	private final MovimentacaoDeEstoqueService movimentacaoDeEstoqueService;
	//ModelMapper não é um componente injetável do Spring,
	//por isso, é necessário implementar uma classe e declarar como um Bean para Spring
	private final ModelMapper modelMapper;

	@Operation(summary = "Busca todos os produtos", description = "Retorna uma lista de produtos")
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	@Operation(summary = "Busca um produto pelo ID", description = "Retorna o produto com o ID fornecido")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
	@GetMapping("/{id}")
	public ResponseEntity<Produto> pesquisar(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Busca um produto pelo ID", description = "Retorna o produto DTO com o ID fornecido")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
	@GetMapping("/dto/{id}")
	public ResponseEntity<ProdutoDTO> pesquisarDTO(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.map(produto -> modelMapper.map(produto, ProdutoDTO.class))
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@Operation(summary = "Insere um novo produto", description = "Adiciona um novo produto ao sistema e retorna os detalhes do produto criado.")
	@ApiResponse(responseCode = "201", description = "Produto criado com sucesso", 
	    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)))
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public Produto inserir(@Valid @RequestBody Produto produto) {
		produto = cadastroProdutoService.salvar(produto);
		return produto;
	}
	
	@Operation(summary = "Atualiza um produto existente", description = "Modifica os detalhes de um produto com o ID fornecido.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", 
	        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
	    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Produto produto) {
		
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			produto.setId(id);
			Produto entidadeAtualizada = cadastroProdutoService.salvar(produto);
			return ResponseEntity.ok(entidadeAtualizada);
		}
	}	
	
	@Operation(summary = "Remove um produto", description = "Exclui um produto do sistema pelo ID fornecido.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
	    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
	})	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			cadastroProdutoService.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
	@Operation(summary = "Repor estoque do produto", description = "Aumenta a quantidade em estoque do produto com base no valor fornecido.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Estoque do produto atualizado com sucesso", 
	        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
	    @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
	    @ApiResponse(responseCode = "400", description = "Quantidade inválida")
	})	
    @PutMapping("/{id}/repor")
    public Produto reporEstoque(@PathVariable Integer id, @RequestParam int quantidade) throws Exception {
    	return movimentacaoDeEstoqueService.reporEstoque(id, quantidade);
    }

	@Operation(summary = "Retirar estoque do produto", description = "Diminui a quantidade em estoque do produto com base no valor fornecido.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Estoque do produto atualizado com sucesso", 
	        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
	    @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
	    @ApiResponse(responseCode = "400", description = "Quantidade inválida ou insuficiente")
	})	
    @PutMapping("/{id}/retirar")
    public Produto retirarEstoque(@PathVariable Integer id, @RequestParam int quantidade) throws Exception {
    	return movimentacaoDeEstoqueService.retirarEstoque(id, quantidade);
    }	
}
