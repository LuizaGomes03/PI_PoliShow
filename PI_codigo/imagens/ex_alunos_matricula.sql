DROP DATABASE IF EXISTS join_alunos_matriculas_cursos;
CREATE DATABASE join_alunos_matriculas_cursos;
USE join_alunos_matriculas_cursos;
-- Criação das tabelas
CREATE TABLE alunos (
id_aluno INT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
data_nascimento DATE,
email VARCHAR(100) UNIQUE
);
CREATE TABLE cursos (
id_curso INT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
carga_horaria INT,
professor VARCHAR(50)
);
CREATE TABLE matriculas (
id_aluno INT,
id_curso INT,
data_matricula DATE NOT NULL,
nota DECIMAL(4, 2),
PRIMARY KEY (id_aluno, id_curso),
FOREIGN KEY (id_aluno) REFERENCES alunos(id_aluno),
FOREIGN KEY (id_curso) REFERENCES cursos(id_curso)
);
-- Populando as tabelas
INSERT INTO alunos VALUES
(1, 'Maria Souza', '1998-05-10', 'maria@email.com'),
(2, 'Pedro Almeida', '1995-12-15', 'pedro@email.com'),
(3, 'Luciana Castro', '1997-08-22', 'luciana@email.com'),
(4, 'Gabriel Ribeiro', '1999-03-30', 'gabriel@email.com'),
(5, 'Beatriz Lima', '1996-11-05', 'beatriz@email.com'),
(6, 'André Santos', '1994-07-18', 'andre@email.com'),
(7, 'Sofia Lima', '1994-07-18', 'sophia@email.com');
INSERT INTO cursos VALUES
(1, 'Banco de Dados', 60, 'Dr. Oliveira'),
(2, 'Programação Python', 80, 'Dra. Pereira'),
(3, 'Desenvolvimento Web', 100, 'Dr. Fernandes'),
(4, 'Inteligência Artificial', 90, 'Dra. Cardoso'),
(5, 'Segurança da Informação', 70, 'Dr. Mendes'),
(6, 'Banco de Dados Não Relacionais', 60, 'Dr. Oliveira');
INSERT INTO matriculas VALUES
(1, 1, '2025-01-15', 8.5),
(1, 2, '2025-02-10', 7.8),
(1, 3, '2025-03-05', 9.0),
(2, 2, '2025-01-20', 6.5),
(2, 4, '2025-02-15', 8.0),
(3, 1, '2025-02-05', 9.5),
(3, 3, '2025-03-10', 7.0),
(3, 5, '2025-04-01', 8.7),
(4, 3, '2025-01-25', 6.8),
(4, 4, '2025-02-20', 7.5),
(5, 1, '2025-03-15', 9.2),
(5, 5, '2025-04-10', 8.0),
(6, 2, '2025-02-01', 7.3);


-- Questão 2.a) - Consulta de 2 Tabelas
-- Liste o nome dos alunos e as datas em que eles se matricularam em algum curso, ordenando pela data de matrícula mais recente.

SELECT a.nome AS nome_aluno, m.data_matricula
FROM alunos a
JOIN matriculas m ON a.id_aluno = m.id_aluno
ORDER BY m.data_matricula DESC;



-- Questão 2.b) - INNER JOIN de 3 Tabelas (1)
-- Liste os nomes dos alunos, os cursos em que estão matriculados e as respectivas notas,
-- apenas para os cursos com carga horária superior a 70 horas.
-- Apresente as soluções com a condição de junção na clásula WHERE e com a sintaxe de JOIN.

SELECT a.nome AS nome_aluno, c.nome AS nome_curso, m.nota
FROM alunos a, cursos c, matriculas m
WHERE a.id_aluno = m.id_aluno
  AND c.id_curso = m.id_curso
  AND c.carga_horaria > 70;

SELECT a.nome AS nome_aluno, c.nome AS nome_curso, m.nota
FROM matriculas m
JOIN alunos a ON a.id_aluno = m.id_aluno
JOIN cursos c ON c.id_curso = m.id_curso
WHERE c.carga_horaria > 70;


-- Questão 2.c) - INNER JOIN de 3 Tabelas (2)
-- Liste os nomes dos alunos, os cursos em que estão matriculados e os nomes dos professores, 
-- apenas para os alunos que obtiveram nota igual ou superior a 8.0 e não informaram email @maua.br.

SELECT a.nome AS nome_aluno, c.nome AS nome_curso, c.professor
FROM matriculas m
JOIN alunos a ON a.id_aluno = m.id_aluno
JOIN cursos c ON c.id_curso = m.id_curso
WHERE m.nota >= 8.0 AND a.email NOT LIKE '%@maua.br';


-- Questão 2.d) - NOT IN ou OUTTER JOIN...
-- Liste todos os cursos sem alunos matriculados. Apresente duas soluções, uma com NOT IN outra com OUTTER JOIN

SELECT nome
FROM cursos
WHERE id_curso NOT IN (
  SELECT DISTINCT id_curso FROM matriculas
);

SELECT c.nome
FROM cursos c
LEFT JOIN matriculas m ON c.id_curso = m.id_curso
WHERE m.id_curso IS NULL;


-- Questão 2.e) - OUTTER JOINs
-- Crie duas listas (dois selects) de nomes dos alunos e os respectivos cursos em que estão matriculados 
-- incluindo na primeira lista mesmo os alunos não matriculados em nenhum curso, e na segunda
-- mesmo os cursos sem nenhum aluno matriculado. Ordene a primeira por nome de curso e a segunda por nome de aluno. 

SELECT a.nome AS nome_aluno, c.nome AS nome_curso
FROM alunos a
LEFT JOIN matriculas m ON a.id_aluno = m.id_aluno
LEFT JOIN cursos c ON c.id_curso = m.id_curso
ORDER BY nome_curso;


SELECT a.nome AS nome_aluno, c.nome AS nome_curso
FROM cursos c
LEFT JOIN matriculas m ON c.id_curso = m.id_curso
LEFT JOIN alunos a ON a.id_aluno = m.id_aluno
ORDER BY nome_aluno;


-- Questão 2.f) - FULL OUTTER JOIN
-- Liste os nomes dos alunos e os respectivos cursos em que estão matriculados incluindo na lista 
-- os alunos não matriculados em nenhum curso, como também os cursos sem nenhum aluno. Ordene por nome de curso. 

-- Alunos com ou sem cursos
SELECT a.nome AS nome_aluno, c.nome AS nome_curso
FROM alunos a
LEFT JOIN matriculas m ON a.id_aluno = m.id_aluno
LEFT JOIN cursos c ON c.id_curso = m.id_curso

UNION

-- Cursos com ou sem alunos
SELECT a.nome AS nome_aluno, c.nome AS nome_curso
FROM cursos c
LEFT JOIN matriculas m ON c.id_curso = m.id_curso
LEFT JOIN alunos a ON a.id_aluno = m.id_aluno

ORDER BY nome_curso;
