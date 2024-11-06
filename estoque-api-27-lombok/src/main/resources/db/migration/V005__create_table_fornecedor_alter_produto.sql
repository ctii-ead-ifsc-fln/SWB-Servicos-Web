create table fornecedor(
	id int not nulL auto_increment,
    nome varchar(50) not null,
    email varchar(100) not null,
    fone varchar(20) not null,
    primary key(id)
);

alter table produto add column id_fornecedor int not null;

set foreign_key_checks=0;

alter table produto add constraint fk_produto_fornecedor
foreign key(id_fornecedor) references fornecedor(id);

SET FOREIGN_KEY_CHECKS = 1;