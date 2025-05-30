SELECT @@default_storage_engine;

CREATE SCHEMA polishow;
USE polishow;

-- tabela usuario
DROP TABLE IF EXISTS tb_usuario CASCADE;
CREATE TABLE tb_usuario
( id_usuario INT PRIMARY KEY AUTO_INCREMENT,
nome_usuario VARCHAR(100) NOT NULL,
email_usuario VARCHAR(50) NOT NULL,
senha_usuario VARCHAR(25) NOT NULL,
adm BOOLEAN NOT NULL
);

-- tabela aluno
DROP TABLE IF EXISTS tb_aluno CASCADE;
CREATE TABLE tb_aluno
(id_usuario_aluno INT PRIMARY KEY REFERENCES tb_usuario(id_usuario)
		ON DELETE CASCADE ON UPDATE CASCADE,
serie VARCHAR(20) NOT NULL);


-- tabela partida
DROP TABLE IF EXISTS tb_partida CASCADE;
CREATE TABLE tb_partida
(id_partida INT PRIMARY KEY AUTO_INCREMENT,
id_usuario_partida INT NOT NULL REFERENCES tb_usuario(id_usuario) ON DELETE RESTRICT ON UPDATE CASCADE
);
SELECT * from tb_partida;
DESCRIBE tb_partida;

-- tabela matéria
DROP TABLE IF EXISTS tb_materia CASCADE;
CREATE TABLE tb_materia
(id_materia SMALLINT PRIMARY KEY AUTO_INCREMENT,
nome_materia VARCHAR(50) NOT NULL
);

-- tabela questões
DROP TABLE IF EXISTS tb_questao CASCADE;
CREATE TABLE tb_questao
( id_questao INT PRIMARY KEY AUTO_INCREMENT,
id_materia SMALLINT NOT NULL REFERENCES tb_materia(id_materia) ON DELETE RESTRICT ON UPDATE CASCADE,
pergunta TEXT NOT NULL,
dificuldade VARCHAR(7) NOT NULL CHECK (UPPER(dificuldade) IN ('FACIL', 'MEDIO','DIFICIL')));

-- tabela alternativas
DROP TABLE IF EXISTS tb_alternativas CASCADE;
CREATE TABLE tb_alternativas
(id_alternativa INT PRIMARY KEY AUTO_INCREMENT,
id_questao INT NOT NULL REFERENCES tb_questao(id_questao) ON DELETE CASCADE ON UPDATE CASCADE,
alternativa VARCHAR(150) NOT NULL,
correta BOOLEAN NOT NULL
);

-- tabela pontuacao
DROP TABLE IF EXISTS tb_pontuacao;
CREATE TABLE tb_pontuacao
(id_pontuacao INT PRIMARY KEY AUTO_INCREMENT,
id_usuario INT NOT NULL REFERENCES tb_usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
id_materia SMALLINT NOT NULL REFERENCES tb_materia(id_materia) ON DELETE CASCADE ON UPDATE CASCADE,
id_partida INT NOT NULL REFERENCES tb_partida(id_partida) ON DELETE CASCADE ON UPDATE CASCADE,
pontos INT NOT NULL
);


