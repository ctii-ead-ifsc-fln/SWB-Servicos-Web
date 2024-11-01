package br.edu.ifsc.fln.vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;

/*Declara que a interface é um componente Spring  
 *Ele é gerenciável pelo container Spring
 */
@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    List<Categoria> findByDescricao(String descricao);
}
