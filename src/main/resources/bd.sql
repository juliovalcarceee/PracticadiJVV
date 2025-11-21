CREATE DATABASE IF NOT EXISTS practica_jvv;
USE practica_jvv;

CREATE TABLE IF NOT EXISTS usuario (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nombre VARCHAR(100)
    );

INSERT INTO usuario (username, password, nombre) VALUES
                                                     ('julio', '1234', 'Julio Valcárcel'),
                                                     ('paula', '9876', 'Paula');
CREATE DATABASE IF NOT EXISTS practica_jvv;
USE practica_jvv;

CREATE TABLE IF NOT EXISTS usuario (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nombre VARCHAR(100)
    );

INSERT INTO usuario (username, password, nombre) VALUES
                                                     ('julio', '1234', 'Julio Valcárcel'),
                                                     ('paula', '9876', 'Paula');
