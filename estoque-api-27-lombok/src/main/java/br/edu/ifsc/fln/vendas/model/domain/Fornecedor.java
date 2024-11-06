package br.edu.ifsc.fln.vendas.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Fornecedor {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String email;
	private String fone;
	
	@OneToMany(mappedBy="fornecedor")
	@JsonIgnoreProperties("fornecedor")
	private List<Produto> produtos = new ArrayList<>();
	
	public void add(Produto produto) {
		this.produtos.add(produto);
		produto.setFornecedor(this);
	}
	
	public void remove(Produto produto) {
		this.produtos.remove(produto);
		produto.setFornecedor(null);
	}
}
