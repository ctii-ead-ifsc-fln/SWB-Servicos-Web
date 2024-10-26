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