# apostas-back-end

---------------------------------------------------------------------
Table:
---------------------------------------------------------------------
CREATE TABLE usuario
(
  oid character varying(38) NOT NULL,
  email character varying(120) NOT NULL,
  password character varying(60),
  nome character varying(60),
  bloqueado boolean,
  admin boolean,
  id_inativo boolean,
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  imagem character varying,
  oid_time character varying(38),
  CONSTRAINT usuario_pkey PRIMARY KEY (oid)
)

CREATE TABLE torneio
(
  oid character varying(38) NOT NULL,
  nome character varying(60),
  imagem character varying,
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT torneio_pkey PRIMARY KEY (oid)
)

CREATE TABLE torneio_usuario
(
  oid character varying(38) NOT NULL,
  oid_usuario character varying(38),
  oid_torneio character varying(38),
  id_bloqueado boolean,
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT torneio_usuario_pk PRIMARY KEY (oid)
)

CREATE TABLE time
(
  oid character varying(38) NOT NULL,
  nome character varying(60),
  imagem character varying,
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT time_pkey PRIMARY KEY (oid)
)

CREATE TABLE torneio_time
(
  oid character varying(38) NOT NULL,
  oid_time character varying(38),
  oid_torneio character varying(38),
  id_bloqueado boolean,
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT torneio_time_pk PRIMARY KEY (oid)
)

CREATE TABLE rodada
(
  oid character varying(38) NOT NULL,
  numero integer,
  oid_usuario character varying(38),
  oid_torneio character varying(38),
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT rodada_pk PRIMARY KEY (oid)
)

CREATE TABLE jogo
(
  oid character varying(38) NOT NULL,
  oid_time1 character varying(38),
  oid_time2 character varying(38),
  placar_time1 integer,
  placar_time2 integer,
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT jogo_pk PRIMARY KEY (oid)
)

CREATE TABLE rodada_jogo
(
  oid character varying(38) NOT NULL,
  oid_jogo character varying(38),
  oid_rodada character varying(38),
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT rodada_jogo_pk PRIMARY KEY (oid)
)

CREATE TABLE jogo_usuario
(
  oid character varying(38) NOT NULL,
  oid_jogo character varying(38),
  oid_usuario character varying(38),
  dt_alteracao timestamp without time zone,
  dt_criacao timestamp without time zone,
  version bigint,
  CONSTRAINT jogo_usuario_pk PRIMARY KEY (oid)
)
---------------------------------------------------------------------

List:
- Jdk 1.8
- Java EE
- Glassfish 4.1
- Plugin Glassfish Java EE
- Eclise/Preferences/Java/Installed JREs/Jdk 1.8
- Postgresql
- - postgresql-9.4-1201.jdbc41.jar
- - C:\glassfish-4.1\glassfish4\glassfish\domains\domain1\lib
- localhost:4848 -> Jdbc Connection Pool
- - User / Password / DatabaseName / ServerName / PortNumber
