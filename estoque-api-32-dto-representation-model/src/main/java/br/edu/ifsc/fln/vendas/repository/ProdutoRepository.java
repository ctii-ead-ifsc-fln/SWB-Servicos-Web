package br.edu.ifsc.fln.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsc.fln.vendas.model.domain.Produto;

@Repository //componente do Spring
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
