/*TABELA ESTOQUE COM RELACIONAMENTO 1:1 PARA PRODUTO*/
create table estoque(
	id_produto int not null references produto(id),
    quantidade int not null default 0,
    qtd_minima int default 0,
    qtd_maxima int default 0,
    situacao enum('ATIVO', 'INATIVO', 'BLOQUEADO') not null default 'ATIVO',
    primary key (id_produto)
) engine innodb;

alter table estoque add constraint fk_estoque_produto 
    foreign key (id_produto) 
    references produto(id) on update cascade on delete cascade;