create database tshirt_games_web;
use tshirt_games_web;

select * from FISICO;
select * from JURIDICO;
select * from PRODUTO;

create table FISICO (
idusuario int not null auto_increment primary key,
email varchar (50),
senha varchar (16),
cpf varchar (15),
nome varchar (20),
sobrenome varchar (40),
datanascimento varchar (10),
sexo varchar (10),
endereco varchar (50),
numero int (10),
complemento varchar (20),
bairro varchar (20),
cidade varchar (20),
estado varchar (20),
cep varchar (10),
condicao varchar (10)
);

create table JURIDICO (
idempresa int not null auto_increment primary key,
email varchar (50),
senha varchar (50),
cnpj varchar (50),
razao varchar (50),
nomefantasia varchar (50),
ie varchar (50),
endereco varchar (50),
numero int (10),
complemento varchar (20),
bairro varchar (50),
cidade varchar (50),
estado varchar (50),
cep varchar (50),
condicao varchar (10)
);

create table PRODUTO (
idproduto int not null auto_increment primary key,
produto varchar(40),	
imagem varchar(200),
descricao varchar (400),
modelo varchar(20),
genero varchar(10),
tamanho varchar(10),
cor varchar(20),
categoria varchar(10), 
valor_custo double(8,2),
valor_venda double(8,2),
quantidade int(8)
);