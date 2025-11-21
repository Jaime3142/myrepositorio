DROP TABLE IF EXISTS alumnos;
CREATE TABLE alumnos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    psp INT,
    ad INT,
    ciber INT,
    ingles INT,
    interfaces INT
);

INSERT INTO alumnos (nombre, psp, ad, ciber, ingles, interfaces) VALUES
('Ivan Garcia', 9, 8, 7, 10, 9),
('Jaime Navas', 6, 7, 8, 5, 7),
('Raul Garzon', 10, 9, 10, 8, 9),
('Pedro Liebana', 7, 6, 7, 7, 6),
('Cristina Ahumada', 8, 9, 8, 9, 10);

