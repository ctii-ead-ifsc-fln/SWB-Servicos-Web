package br.edu.ifsc.fln.vendas.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "id_produto")
	private int id;
	@Getter
	private int quantidade;//quantidade atual
	@Column(name = "qtd_maxima")
	private int quantidadeMaxima;
	@Column(name = "qtd_minima")
	private int quantidadeMinima;
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao")
	private ESituacao eSituacao = ESituacao.ATIVO;
	
    @OneToOne
    @MapsId // Garante que o id do Estoque seja igual ao id do Produto
    @JoinColumn(name = "id_produto")
    @JsonIgnoreProperties("estoque")
    private Produto produto;
	
    public void repor(int quantidade) throws Exception {
    	if (this.eSituacao != ESituacao.ATIVO) {
            throw new IllegalStateException("Estoque inativo ou bloqueado, retirada não permitida.");
        }
    	if (this.quantidade + quantidade > this.quantidadeMaxima) {
            throw new Exception("Quantidade excede o máximo permitido no estoque");
        }
        this.quantidade += quantidade;
    }

    public void retirar(int quantidade) throws Exception {
    	if (this.eSituacao != ESituacao.ATIVO) {
            throw new IllegalStateException("Estoque inativo ou bloqueado, retirada não permitida.");
        }
        if (this.quantidade - quantidade < 0) {
            throw new Exception("Quantidade insuficiente no estoque");
        }
        this.quantidade -= quantidade;
    }
}
