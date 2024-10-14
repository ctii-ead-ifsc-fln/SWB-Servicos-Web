package br.edu.ifsc.fln.vendas.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;

@RestController
public class CategoriaController {

	@GetMapping("/categorias")
	public List<Categoria> listar() {
		//criando alguns dados fictícios
		List<Categoria> categorias = new ArrayList<>();
		Categoria c1 = new Categoria();
		c1.setId(1);
		c1.setDescricao("Vestuário");
		Categoria c2 = new Categoria();
		c2.setId(2);
		c2.setDescricao("Calçado");
		//inserindo os dados na lista
    	categorias.add(c1);
    	categorias.add(c2);
		return categorias;//retornando uma coleção
	}
}

