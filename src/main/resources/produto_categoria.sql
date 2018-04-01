create table produto_categoria(
    id serial,
    nome varchar,
    primary key(id)
);
insert into produto_categoria (nome) values ('Cervejas');
insert into produto_categoria (nome) values ('Doces e Sobremesas');
insert into produto_categoria (nome) values ('Lanches');
insert into produto_categoria (nome) values ('Pizzas');
insert into produto_categoria (nome) values ('Poções');
insert into produto_categoria (nome) values ('Refeições');
insert into produto_categoria (nome) values ('Refrigerantes');
insert into produto_categoria (nome) values ('Sucos');