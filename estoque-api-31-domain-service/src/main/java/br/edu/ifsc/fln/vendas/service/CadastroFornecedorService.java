package br.edu.ifsc.fln.vendas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsc.fln.vendas.exceptions.NegocioEstoqueException;
import br.edu.ifsc.fln.vendas.model.domain.Fornecedor;
import br.edu.ifsc.fln.vendas.repository.FornecedorRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroFornecedorService {
	
	private final FornecedorRepository fornecedorRepository;
	
	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {
		boolean haEmail = fornecedorRepository.findByEmail(fornecedor.getEmail())
				.filter(f -> !f.equals(fornecedor))
				.isPresent(); 
		if (haEmail) {
			throw new NegocioEstoqueException("Este email já existe e não pode ser utilizado!");
		}		
		return fornecedorRepository.save(fornecedor);
	}
	
	@Transactional
	public void excluir(Integer id) {
		fornecedorRepository.deleteById(id);
	}
}
