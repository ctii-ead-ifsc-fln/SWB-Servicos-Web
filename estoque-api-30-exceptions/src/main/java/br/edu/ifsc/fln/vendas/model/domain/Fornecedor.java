package br.edu.ifsc.fln.vendas.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "O nome do fornecedor é obrigatório.")
	@Size(max = 50, message = "O nome não pode ter mais de 50 caracteres.")
	private String nome;
	@NotBlank(message = "O email do fornecedor é obrigatório.")
	@Size(max = 100, message = "O email não pode ter mais de 100 caracteres.")
	@Email
	private String email;
	@NotBlank(message = "O fone do fornecedor é obrigatório.")
	@Size(max = 20, message = "O fone não pode ter mais de 20 caracteres.")
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
