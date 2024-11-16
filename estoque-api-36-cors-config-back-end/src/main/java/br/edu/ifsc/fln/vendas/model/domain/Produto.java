package br.edu.ifsc.fln.vendas.model.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Getter e Setter no escopo da classe, 
//vale para todos os atributos.
@Getter
@Setter 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	 //não aceita valor em branco e nulo
	@NotBlank(message = "O nome do produto é obrigatório.")
	@Size(max = 50, message = "O nome não pode ter mais de 50 caracteres.")
	private String nome;
	@NotBlank(message = "A descrição do produto é obrigatória.")
	@Size(max = 200, message = "A descrição não pode ter mais de 200 caracteres.")
	private String descricao;
	
	@NotNull(message = "O preço do produto é obrigatório.") 
	private BigDecimal preco;
	
	@NotNull(message = "A categoria do produto é obrigatório.")
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@NotNull(message = "O fornecedor do produto é obrigatório.")
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	@JsonIgnoreProperties("produtos")
	private Fornecedor fornecedor;
	
	@Getter //
	@OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("produto")
	private Estoque estoque;	

    public Produto() {
        this.estoque = new Estoque(); // Associação por composição
        this.estoque.setProduto(this);
    }

}
