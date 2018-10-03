CREATE SEQUENCE poc.endereco_sequence START 1;
CREATE SEQUENCE poc.produto_sequence START 1;
CREATE SEQUENCE poc.item_pedido_sequence START 1;
CREATE SEQUENCE poc.arquivos_midia_sequence START 1;
CREATE SEQUENCE poc.avaliacao_sequence START 1;
CREATE SEQUENCE poc.fornecedor_sequence START 1;
CREATE SEQUENCE poc.pedido_sequence START 1;
CREATE SEQUENCE poc.ordem_entrega_sequence START 1;
--********************8
--      FORNECEDOR
--********************8
-- DROP TABLE poc.fornecedor;

CREATE TABLE poc.fornecedor
(
  id integer NOT NULL,
  nome character varying(100),
  cnpj bigint,
  url_site character varying(100),
  versao_api integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.fornecedor
  OWNER TO postgres;
GRANT ALL ON TABLE poc.fornecedor TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.fornecedor TO public;

CREATE UNIQUE INDEX xpkfornecedor
  ON poc.fornecedor
  USING btree
  (id);

--INSERT DADOS INICIAIS
insert into poc.fornecedor (id, nome, cnpj, url_site, versao_api) values 
(1, 'Jibson s.a', 123567232000101, 'http://localhost:10001/api/', 1),
(2, 'Femder s.a', 125422722233101, 'http://localhost:10011/api/', 1);


--********************8
--      USUARIO
--********************8



-- DROP TABLE poc.usuario;

CREATE TABLE poc.usuario
(
  id character varying(80) NOT NULL,
  responsavel character varying(80),
  perfil character varying(20),
  email character varying(60)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.usuario
  OWNER TO postgres;
GRANT ALL ON TABLE poc.usuario TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.usuario TO public;

-- Index: poc.xpkusuario

-- DROP INDEX poc.xpkusuario;

CREATE UNIQUE INDEX xpkusuario
  ON poc.usuario
  USING btree
  (id COLLATE pg_catalog."default");

--INSERT DADOS INICIAIS  
insert into poc.usuario (id, email, perfil, responsavel) values 
('admin', 'pocadm092018@gmail.com', 'admin', null),
('ahoras', 'ahoras@gmail.com', 'admin', null),
('userweb', 'pocuser092018@gmail.com', 'userweb', null);



--********************8
--      ENDERECO
--********************8


DROP TABLE poc.endereco;

CREATE TABLE poc.endereco
(
  id integer NOT NULL,
  nome character varying(40),
  cidade character varying(80),
  endereco character varying(200),
  cep integer,
  enderecopadrao character(1),
  idusuario character varying(80) NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.endereco
  OWNER TO postgres;
GRANT ALL ON TABLE poc.endereco TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.endereco TO public;

-- Index: poc.xpkendereco

-- DROP INDEX poc.xpkendereco;

CREATE UNIQUE INDEX xpkendereco
  ON poc.endereco
  USING btree
  (id);



item_pedido

insert into poc.endereco (id, nome, cidade, endereco, cep, enderecopadrao, idusuario) values 
(1, 'adminHome', 'Sao Paulo', 'Rua Itarare 280 - Bela Vista', 1308030, 'S',  'admin');


-- Table: poc.produto

-- DROP TABLE poc.produto;

CREATE TABLE poc.produto
(
  id integer NOT NULL,
  nome character varying(100),
  categoria character varying(100),
  descricao character varying,
  preco numeric(15,2),
  detalhe character varying,
  idfornecedor integer NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.produto
  OWNER TO postgres;
GRANT ALL ON TABLE poc.produto TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.produto TO public;

-- Index: poc.xpkproduto

-- DROP INDEX poc.xpkproduto;

CREATE UNIQUE INDEX xpkproduto
  ON poc.produto
  USING btree
  (id);
insert into poc.produto (id, nome, categoria, descricao, preco, detalhe, idfornecedor) values 
(1, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 8000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(2, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 9000.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(3, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 8500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1);




--*******************
--    PEDIDO
--*******************
--DROP TABLE poc.pedido;

CREATE TABLE poc.pedido
(
  id integer NOT NULL,
  idusuario character varying(80) NOT NULL,
  status integer,
  destinatario  character varying(200) NOT NULL,
  endereco_entrega  character varying(200) NOT NULL,
  valor_total numeric(15,2)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.pedido
  OWNER TO postgres;
GRANT ALL ON TABLE poc.pedido TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.pedido TO public;

-- Index: poc.xpkpedido

-- DROP INDEX poc.xpkpedido;

CREATE UNIQUE INDEX xpkpedido
  ON poc.pedido
  USING btree
  (id);

insert into poc.pedido (id, idusuario, status, destinatario, endereco_entrega, valor_total) values 
(1, 'ahoras', 1, 'Adriano Horas', 'Rua itarare 177 apto 93', 8000.00);

-- Table: poc.itempedido

-- DROP TABLE poc.itempedido;

CREATE TABLE poc.itempedido
(
  idproduto integer NOT NULL,
  quantidade integer,
  idpedido integer NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.itempedido
  OWNER TO postgres;
GRANT ALL ON TABLE poc.itempedido TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.itempedido TO public;

-- Index: poc.xpkitempedido

-- DROP INDEX poc.xpkitempedido;

CREATE UNIQUE INDEX xpkitempedido
  ON poc.itempedido
  USING btree
  (idproduto, idpedido);


insert into poc.itempedido (idproduto, quantidade, idpedido) values 
(1, 2, 1);



-- Table: poc.arquivosmidia

-- DROP TABLE poc.arquivosmidia;

CREATE TABLE poc.arquivosmidia
(
  id integer NOT NULL,
  tipo integer,
  uri character varying(100),
  idproduto integer NOT NULL,
  sequencia integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.arquivosmidia
  OWNER TO postgres;
GRANT ALL ON TABLE poc.arquivosmidia TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.arquivosmidia TO public;

-- Index: poc.xpkarquivosmidia

-- DROP INDEX poc.xpkarquivosmidia;

CREATE UNIQUE INDEX xpkarquivosmidia
  ON poc.arquivosmidia
  USING btree
  (id);

insert into poc.arquivosmidia (id, tipo, uri, idproduto, sequencia) values 
(1, 1, 'https://www.gettyimages.pt/detail/foto/electric-guitar-imagem-royalty-free/79228304', 1, 1),
(3, 1, 'https://www.gettyimages.pt/detail/foto/guitar-against-white-background-imagem-royalty-free/674496725', 2, 1),
(2, 1, 'https://www.gettyimages.pt/detail/foto/guitar-against-white-background-imagem-royalty-free/913931098', 3, 1);



- Table: poc.pedido

-- DROP TABLE poc.pedido;

CREATE TABLE poc.pedido
(
  id integer NOT NULL,
  idusuario character varying(80) NOT NULL,
  status integer,
  destinatario character varying(200) NOT NULL,
  endereco_entrega character varying(200) NOT NULL,
  valor_total numeric(15,2),
  data_pedido integer
 
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.pedido
  OWNER TO postgres;
GRANT ALL ON TABLE poc.pedido TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.pedido TO public;

-- Index: poc.xpkpedido

-- DROP INDEX poc.xpkpedido;

CREATE UNIQUE INDEX xpkpedido
  ON poc.pedido
  USING btree
  (id);

-- Table: poc.itempedido

-- DROP TABLE poc.item_pedido;

CREATE TABLE poc.item_pedido
(
  idproduto integer NOT NULL,
  quantidade integer,
  idpedido integer NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.item_pedido
  OWNER TO postgres;
GRANT ALL ON TABLE poc.item_pedido TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.item_pedido TO public;

-- Index: poc.xpkitempedido

-- DROP INDEX poc.xpkitempedido;

CREATE UNIQUE INDEX xpkitempedido
  ON poc.item_pedido
  USING btree
  (idproduto, idpedido);


--DROP TABLE poc.ordem_entrega;
-- Table: poc.ordementrega

-- DROP TABLE poc.ordem_entrega;

CREATE TABLE poc.ordem_entrega
(
  id integer NOT NULL,
  idfornecedor integer NOT NULL,
  idpedido integer NOT NULL,
  destinatario character varying(200) NOT NULL,
  endereco_entrega character varying(200) NOT NULL,
  codrastreamento character varying(50),
  status integer

)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.ordem_entrega
  OWNER TO postgres;
GRANT ALL ON TABLE poc.ordem_entrega TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.ordem_entrega TO public;

-- Index: poc.xpkordem_de_entrega

-- DROP INDEX poc.xpkordem_de_entrega;

CREATE UNIQUE INDEX xpkordem_de_entrega
  ON poc.ordem_entrega
  USING btree
  (id);



CREATE TABLE poc.item_ordem_entrega
(
  idproduto integer NOT NULL,
  quantidade integer,
  idordem integer NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.item_ordem_entrega
  OWNER TO postgres;
GRANT ALL ON TABLE poc.item_ordem_entrega TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.item_ordem_entrega TO public;

CREATE UNIQUE INDEX xpkitem_ordem_entrega
  ON poc.item_ordem_entrega
  USING btree
  (idordem, idproduto);