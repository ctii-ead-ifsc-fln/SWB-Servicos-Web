package br.edu.ifsc.fln.vendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsc.fln.vendas.model.domain.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{
	Optional<Fornecedor> findByEmail(String email);
}
