package br.edu.ifsc.fln.vendas.model.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Estoque {
	@Id
	private int id;
	private int quantidade;//quantidade atual
	private int quantidadeMaxima;
	private int quantidadeMinima;
	private ESituacao eSituacao = ESituacao.ATIVO;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id") // O ID de Estoque será o mesmo que o de Produto
	private Produto produto;
	
	public Estoque() {}

    public Estoque(Produto produto) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estoque other = (Estoque) obj;
		return id == other.id;
	}
    
    
}
