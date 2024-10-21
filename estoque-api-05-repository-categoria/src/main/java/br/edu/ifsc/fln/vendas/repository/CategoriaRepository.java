package br.edu.ifsc.fln.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsc.fln.vendas.model.domain.Categoria;

/*Declara que a interface é um componente Spring  
 *Ele é gerenciável pelo container Spring
 */
@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    //A partir desta interface o Spring Data JPA (SDJPA) nos fornece
    //uma implementação da classe que contém os principais
    //métodos CRUD para transação com banco de dados.
}
