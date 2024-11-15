package br.edu.ifsc.fln.vendas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;
import br.edu.ifsc.fln.vendas.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroCategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	@Transactional
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Transactional
	public void excluir(Integer id) {
		categoriaRepository.deleteById(id);
	}
	
	
}
