package br.edu.ifsc.fln.vendas.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
	private Integer id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String descricaoCategoria;
}
