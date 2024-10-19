package br.edu.ifsc.fln.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsc.fln.domain.Produto;

@Service
public class ProdutoService {
	List<Produto> produtos = new ArrayList<>();
	
	public Produto create(Produto produto) {
		produtos.add(produto);
		return produtos.get(produtos.size()-1);
	}
	
	public List<Produto> getAll() {
		return produtos;
	}
	
	public Produto get(int id) {
		for (Produto p: produtos) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	public void delete(int id) {
		for (Produto p: produtos) {
			if (p.getId() == id) {
				produtos.remove(p);
				return;
			}
		}
	}
	
	public Produto update(Produto produto) {
		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getId() == produto.getId()) {
				produtos.set(i, produto);
				return produtos.get(i);
			}
		}
		return null;
	}
}
