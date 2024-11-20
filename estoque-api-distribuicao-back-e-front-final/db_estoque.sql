CREATE DATABASE IF NOT EXISTS db_estoque;
USE db_estoque;

create table Categoria (
	id int not null auto_increment,
    descricao varchar(100) not null,
    
    primary key(id)
);

CREATE TABLE produto(
   id int NOT NULL auto_increment,
   nome varchar(50) not null,
   descricao varchar(200),
   preco decimal(10,2) not null,
   id_categoria int not null ,
   primary key(id)
);

alter table produto add constraint fk_produto_categoria
foreign key(id_categoria) references categoria(id);

create table fornecedor(
	id int not nulL auto_increment,
    nome varchar(50) not null,
    email varchar(100) not null,
    fone varchar(20) not null,
    primary key(id)
);

alter table produto add column id_fornecedor int not null;

alter table produto add constraint fk_produto_fornecedor
foreign key(id_fornecedor) references fornecedor(id);

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