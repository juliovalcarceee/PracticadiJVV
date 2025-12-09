DROP DATABASE IF EXISTS practicadjvv;
CREATE DATABASE practicadjvv;
USE practicadjvv;

CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL,
                          password VARCHAR(50) NOT NULL,
                          nombre VARCHAR(100) NOT NULL
);

INSERT INTO usuarios (username, password, nombre) VALUES
                                                      ('julio', '1234', 'Julio Valcárcel'),
                                                      ('admin', 'admin', 'Administrador');
