CREATE DATABASE exemploLogin;
USE exemploLogin;

CREATE TABLE cadProduto (produto VARCHAR(100), preco double, estoque double);
CREATE TABLE cadUsuario (id INT PRIMARY KEY auto_increment, nome VARCHAR(100), senha VARCHAR(20));
CREATE TABLE cadCliente (nome VARCHAR(100),rg VARCHAR(12),cpf VARCHAR(14),telefone VARCHAR(15),email VARCHAR(100));