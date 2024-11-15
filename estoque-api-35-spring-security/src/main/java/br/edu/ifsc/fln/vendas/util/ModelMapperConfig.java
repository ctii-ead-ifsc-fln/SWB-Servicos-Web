package br.edu.ifsc.fln.vendas.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifsc.fln.vendas.dto.ProdutoDTO;
import br.edu.ifsc.fln.vendas.model.domain.Produto;

@Configuration //anotação para configurar Beans para tornar componente gerenciável pelo Spring
public class ModelMapperConfig {
	
	@Bean //configura um método como Bean gerenciável pelo Spring
	public ModelMapper modelMapper() {
		ModelMapper  modelMapper = new ModelMapper(); //instância que será retornada para o Spring
		modelMapper.createTypeMap(Produto.class, ProdutoDTO.class)
			.addMappings(mapper -> mapper.map(Produto::getPreco, ProdutoDTO::setPrecoAtual));
		return modelMapper; 	
    }

}
