package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;
import br.edu.ifsc.fln.vendas.repository.CategoriaRepository;

@RestController
public class CategoriaController {
	
	/* Anotação @Autowired utilizada para injeção de dependência
	 * Não é tão recomendada. Optou-se então por um construtor
	 * para iniciar com uma instância de repository gerenciável
	 * pelo Spring.
	 */
	//@Autowired 
	private final CategoriaRepository categoriaRepository;
	
	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@GetMapping("/categorias")
	public List<Categoria> listar() {
		//findAll - uma implementação provida pelo SDJPA - Spring Data JPA 
		return categoriaRepository.findAll();
	}
}

