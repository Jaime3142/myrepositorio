
CREATE TABLE cliente(
id_cliente int auto_increment primary key,
nombre varchar(50),
edad int,
ciudad varchar(50)
);

CREATE TABLE pedidos(
id_pedido int auto_increment primary key,
fecha date,
id_cliente int,
foreign key (id_cliente) references cliente (id_cliente)
);

CREATE TABLE producto(
id_producto int auto_increment primary key,
precio decimal(10,2),
nombre varchar(50),
stock int
);

CREATE TABLE pedido_producto(
id_producto int ,
id_pedido int,
primary key(id_producto, id_pedido),
cantidad int,
foreign key (id_producto) references producto (id_producto),
foreign key (id_pedido) references pedidos (id_pedido)
);

INSERT INTO cliente(nombre,ciudad,edad) values
('Ana Lopez','Madrid', 30),
('Carlos Ruiz','Sevilla',45),
('Lucía Gómez','Granada',22);

INSERT INTO producto(nombre,precio,stock) values
('Portatil',800.00,2),
('Ratón',10,50),
('Teclado',20,50);

INSERT INTO pedidos(fecha,id_cliente) values
('2024-01-10',1),
('2024-01-11',2),
('2024-01-12',3),
('2024-05-05',1);

INSERT INTO pedido_producto(id_pedido,id_producto,cantidad) values
(1,1,1),
(1,2,2),
(2,3,2),
(3,1,1),
(4,3,5);



