
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

@Entity
public class Estoque {
	@Id
	@Column(name = "id_produto")
	private int id;
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
	
    // Método para associar o Produto ao Estoque
    public void setProduto(Produto produto) {
        this.produto = produto;
    }  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantidadeMaxima() {
		return quantidadeMaxima;
	}
	public void setQuantidadeMaxima(int quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}
	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}
	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public ESituacao getESituacao() {
		return eSituacao;
	}
	public void setESituacao(ESituacao eSituacao) {
		this.eSituacao = eSituacao;
	}
	
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
