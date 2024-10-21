package br.edu.ifsc.fln.vendas.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@RestController
public class CategoriaController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping("/categorias")
	public List<Categoria> listar() {
		TypedQuery<Categoria> pesquisa = 
				entityManager.createQuery("from Categoria", Categoria.class);
		return pesquisa.getResultList();
	}
}

