delete from pedido_pagamento;
delete from pedido;
delete from cliente;
delete from empresa;
delete from entregador;
delete from produto;
delete from usuario;

alter sequence pedido_pagamento_id_seq restart;
alter sequence pedido_id_seq restart;
alter sequence cliente_id_seq restart;
alter sequence empresa_id_seq restart;
alter sequence entregador_id_seq restart;
alter sequence produto_id_seq restart;
alter sequence usuario_id_seq restart;