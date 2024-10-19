package br.edu.ifsc.fln.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


//instruções para instação lombok - https://ia-tec-development.medium.com/lombok-como-instalar-na-spring-tool-suite-4-ide-48defb1d0eb9
@Getter
@Setter
public class Produto {
	private int id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private int quantidade;
}
