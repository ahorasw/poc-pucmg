DROP SEQUENCE poc.endereco_sequence;
DROP SEQUENCE poc.produto_sequence;
DROP SEQUENCE poc.item_pedido_sequence;
DROP SEQUENCE poc.arquivos_midia_sequence;
DROP SEQUENCE poc.avaliacao_sequence;
DROP SEQUENCE poc.fornecedor_sequence;
DROP SEQUENCE poc.pedido_sequence;
DROP SEQUENCE poc.ordem_entrega_sequence;

DROP TABLE poc.fornecedor;
DROP TABLE poc.usuario;
DROP TABLE poc.endereco;
DROP TABLE poc.produto;
DROP TABLE poc.arquivosmidia;
DROP TABLE poc.pedido;
DROP TABLE poc.item_pedido;
DROP TABLE poc.ordem_entrega;
DROP TABLE poc.item_ordem_entrega;


CREATE SEQUENCE poc.endereco_sequence START 1;
CREATE SEQUENCE poc.produto_sequence START 1;
CREATE SEQUENCE poc.item_pedido_sequence START 1;
CREATE SEQUENCE poc.arquivos_midia_sequence START 1;
CREATE SEQUENCE poc.avaliacao_sequence START 1;
CREATE SEQUENCE poc.fornecedor_sequence START 1;
CREATE SEQUENCE poc.pedido_sequence START 1;
CREATE SEQUENCE poc.ordem_entrega_sequence START 1;


GRANT USAGE ON SEQUENCE poc.produto_sequence TO public;
GRANT USAGE ON SEQUENCE poc.endereco_sequence TO public;
GRANT USAGE ON SEQUENCE poc.produto_sequence TO public;
GRANT USAGE ON SEQUENCE poc.item_pedido_sequence TO public;
GRANT USAGE ON SEQUENCE poc.arquivos_midia_sequence TO public;
GRANT USAGE ON SEQUENCE poc.avaliacao_sequence TO public;
GRANT USAGE ON SEQUENCE poc.fornecedor_sequence TO public;
GRANT USAGE ON SEQUENCE poc.pedido_sequence TO public;
GRANT USAGE ON SEQUENCE poc.ordem_entrega_sequence TO public;

--********************8
--      FORNECEDOR
--********************8
-- 
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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.fornecedor TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.fornecedor TO public;

CREATE UNIQUE INDEX xpkfornecedor
  ON poc.fornecedor
  USING btree
  (id);

--INSERT DADOS INICIAIS
insert into poc.fornecedor (id, nome, cnpj, url_site, versao_api) values 
(1, 'Jibson s.a', 123567232000101, 'http://localhost:10001/api/fornecedor', 1),
(2, 'Femder s.a', 125422722233101, 'http://localhost:10011/api/fornecedor', 1);


--********************8
--      USUARIO
--********************8



-- 



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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.usuario TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.usuario TO public;

-- Index: poc.xpkusuario

-- DROP INDEX poc.xpkusuario;

CREATE UNIQUE INDEX xpkusuario
  ON poc.usuario
  USING btree
  (id COLLATE pg_catalog."default");

--INSERT DADOS INICIAIS  
insert into poc.usuario (id, email, perfil, responsavel) values 
('pocadm092018@gmail.com', 'pocadm092018@gmail.com', 'admin', null),
('ahoras@gmail.com', 'pocuser092018@gmail.com', 'admin', null),
('pocuser092018@gmail.com', 'pocuser092018@gmail.com', 'userweb', null);



--********************8
--      ENDERECO
--********************8



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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.endereco TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.endereco TO public;

-- Index: poc.xpkendereco

-- DROP INDEX poc.xpkendereco;

CREATE UNIQUE INDEX xpkendereco
  ON poc.endereco
  USING btree
  (id);



insert into poc.endereco (id, nome, cidade, endereco, cep, enderecopadrao, idusuario) values 
(1, 'adminHome', 'Sao Paulo', 'Rua Itarare 280 - Bela Vista', 1308030, 'S',  'admin');


-- Table: poc.produto

-- 


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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.produto TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.produto TO public;

-- Index: poc.xpkproduto

-- DROP INDEX poc.xpkproduto;

CREATE UNIQUE INDEX xpkproduto
  ON poc.produto
  USING btree
  (id);
insert into poc.produto (id, nome, categoria, descricao, preco, detalhe, idfornecedor) values 
(1, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 4500.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(2, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 7000.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(3, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 6500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(4, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 2000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(5, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 950.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(6, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 3500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(7, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 3000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(8, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 2200.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(9, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 8100.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(10, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 8000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(11, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 3200.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(12, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(13, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 8000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(14, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 900.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(15, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 840.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(16, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 6500.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(17, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 9400.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(18, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 750.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(19, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 8000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(20, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 2300.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(21, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 2500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(22, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 9000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(23, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 9000.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(24, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 1500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(25, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 5000.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(26, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 9000.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(27, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 3500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(28, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 700.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(29, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 9000.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(30, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 4500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1),
(31, 'Guitarra Fender Strato USA - Sunburst', 'Instrumentos Musicais', 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018', 800.00, 'Guitarra Fender modelo: Stratocaster made in USA. ano fabricacao: 2018 visiste o site do fabricante: http://www.fender.com ', 1),
(32, 'Guitarra Gibson SG', 'Instrumentos Musicais', 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018', 9000.00, 'Guitarra Gibson modelo: SG made in USA. ano fabricacao: 2018: http://www.gibson.com ',   1),
(33, 'Violao acustico modelo - EG200', 'Instrumentos Musicais', 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018', 500.00, 'Violao Fender modelo: Classico made in USA. ano fabricacao: 2018 ', 1);






--*******************
--    PEDIDO
--*******************
--

-- Table: poc.itempedido

--


-- Table: poc.arquivosmidia

-- 



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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.arquivosmidia TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.arquivosmidia TO public;

-- Index: poc.xpkarquivosmidia

-- DROP INDEX poc.xpkarquivosmidia;

CREATE UNIQUE INDEX xpkarquivosmidia
  ON poc.arquivosmidia
  USING btree
  (id);

insert into poc.arquivosmidia (id, tipo, uri, idproduto, sequencia) values 
(1, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo1.jpeg', 1, 1),
(2, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo2.jpeg', 2, 1),
(3, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo3.jpeg', 3, 1),
(4, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo4.jpeg', 4, 1),
(5, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo5.jpeg', 5, 1),
(6, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo6.jpeg', 6, 1),
(7, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo7.jpeg', 7, 1),
(8, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo8.jpeg', 8, 1),
(9, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo9.jpeg', 9, 1),
(10, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/baixo10.jpeg', 10, 1),
(11, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/violao1.jpeg', 11, 1),
(12, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/violao2.jpeg', 12, 1),
(13, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/violao3.jpeg', 13, 1),
(14, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/violao4.jpeg', 14, 1),
(15, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/violao5.jpeg', 15, 1),
(16, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra1.jpeg', 16, 1),
(17, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra2.jpeg', 17, 1),
(18, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra3.jpeg', 18, 1),
(19, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra4.jpeg', 19, 1),
(20, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra5.jpeg', 20, 1),
(21, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra6.jpeg', 21, 1),
(22, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra7.jpeg', 22, 1),
(23, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra8.jpeg', 23, 1),
(24, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra9.jpeg', 24, 1),
(25, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra10.jpeg', 25, 1),
(26, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra11.jpeg', 26, 1),
(27, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra12.jpeg', 27, 1),
(28, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra13.jpeg', 28, 1),
(29, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra14.jpeg', 29, 1),
(30, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra15.jpeg', 30, 1),
(31, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra16.jpeg', 31, 1),
(32, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra17.jpeg', 32, 1),
(33, 1, 'https://s3-sa-east-1.amazonaws.com/poc.rocks.ahorasw.frontendspa/assets/img/guitarra18.jpeg', 33, 1);
-- Table: poc.pedido




CREATE TABLE poc.pedido
(
  id integer NOT NULL,
  idusuario character varying(80) NOT NULL,
  status integer,
  destinatario character varying(200) NOT NULL,
  endereco_entrega character varying(200) NOT NULL,
  valor_total numeric(15,2),
  data_pedido date
 
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poc.pedido
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.pedido TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.pedido TO public;


CREATE UNIQUE INDEX xpkpedido
  ON poc.pedido
  USING btree
  (id);

-- Table: poc.itempedido



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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.item_pedido TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.item_pedido TO public;

-- Index: poc.xpkitempedido

-- DROP INDEX poc.xpkitempedido;

CREATE UNIQUE INDEX xpkitempedido
  ON poc.item_pedido
  USING btree
  (idproduto, idpedido);


-- Table: poc.ordementrega

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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.ordem_entrega TO pocuser;
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
  OWNER TO pocuser;
GRANT ALL ON TABLE poc.item_ordem_entrega TO pocuser;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE poc.item_ordem_entrega TO public;

CREATE UNIQUE INDEX xpkitem_ordem_entrega
  ON poc.item_ordem_entrega
  USING btree
  (idordem, idproduto);