package br.edu.ifsc.fln.vendas.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
	private Integer id;
	private String nome;
	private String descricaoProduto;
	/* O atributo preco foi alterado para precoAtual.
	 * Modificando o nome do atributo para forçar um  exemplo
	 * onde o ModelMapper não mais reconhecerá o nome do atributo naturalmente,
	 * por conta  das estratégias de correspondência do ModelMapper.
	 * Será necessária a implementação explicita para esse mapeamento, 
	 * haja vista que, o identificador (nome) do atributo precoAtual 
	 * difere de preco na classe Produto. Isto é diferente de descricaoProduto, pois
	 * nete caso há uma correspondência passível de reconhecimento,
	 * isto é, descricaoProduto é descricao do Produto.
	 * Esse mapeamento será realizado no médoto modelMapper da classe
	 * ModelMapperConfig.
	 */
	private BigDecimal precoAtual;								
	private String descricaoCategoria;
}
