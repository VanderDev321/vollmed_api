CREATE TABLE pacientes(
    id bigint not null auto_increment,
    nome varchar(100) not null ,
    email varchar(100) not null unique ,
    telefone varchar(40)not null ,
    cpf varchar(11) not null unique ,
    logradouro varchar(100) not null ,
    bairro varchar(100) not null ,
    cidade varchar(100) not null ,
    uf varchar(2) not null ,
    cep varchar(11)not null ,
    numero varchar(10),
    complemento varchar(100),
    primary key (id)
);