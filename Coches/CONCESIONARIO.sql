DROP type VehiculoType force;
DROP type MarcaType force;
DROP type CocheType force;

DROP table Marca cascade constraints;
DROP table Vehiculo cascade constraints;
DROP table Coche cascade constraints;


-- CREAR UN TIPO OBJETO LLAMADO VehiculoType
create or replace type VehiculoType as object(
IdVehiculo number,
nombre varchar2(30)
)not final; --Esto permite que otros tipos hereden de este
/

-- CREAR UN TIPO OBJETO LLAMADO MarcaType
create or replace type MarcaType as object(
idMarca number,
nombre varchar2(30)
); --Si no ponemos nada no te permite heredar
/

--varray de 3 colores(posiciones) MAXIMO 3
create or replace type ColoresType as VARRAY(3) OF VARCHAR2(20);
/
--tabla anidada de reparaciones
create or replace type ReparacionesType as TABLE of VARCHAR2(50);
/

--objeto coche
--under es como extends en java hereda de ...
create or replace type CocheType under VehiculoType(
precio number,
marca REF MarcaType,
colores ColoresType,
reparaciones  ReparacionesType
);
/

create table Marca of MarcaType(
IdMarca primary key
);
/

create table Coche of CocheType(
primary key (IdVehiculo),
scope for (marca) is Marca --le dice a Oracle que todas las referencias apunten a filas de la tabla Marca
)
nested table reparaciones store as Reparaciones_Stores;
/

insert into Marca values (MarcaType(1,'Suzuki'));
insert into Marca values (MarcaType(2,'Mercedes'));
insert into Marca values (MarcaType(3,'Jaime_Tesla'));
insert into Marca values (MarcaType(4,'Jaime_Hyundai'));
/

insert into Coche
select CocheType(
1,'4x4',12000, ref(m),ColoresType('Gris','Negro'), ReparacionesType('Cambio Ruedas', 'Tubo de escape')
)from Marca m where m.idmarca = 1;
/

insert into Coche
select CocheType(
2,'Benz',20000, ref(m),ColoresType('Blanco','Negro'), ReparacionesType('Cambio Aceite', 'Frenos')
)from Marca m where m.idmarca = 2;
/
insert into Coche
select CocheType(
3,'Model 3',50000, ref(m),ColoresType('Rojo','Negro'), ReparacionesType('Cambio Liquido', 'Maletero')
)from Marca m where m.idmarca = 3;
/
insert into Coche
select CocheType(
4,'Ioniq 5',25000, ref(m),ColoresType('Verde','Negro'), ReparacionesType('Motor', 'Luneta')
)from Marca m where m.idmarca = 4;

--ver todas las marcas
select * from Marca;
-- ver todos los coches
select * from Coche;

--ver coches con nombre de la marca(usando deref)
select c.idvehiculo,c.nombre,c.precio, deref(c.marca).idMarca as idMarca, deref(c.marca).nombre as NombreMarca from Coche c;

--ordenar por nombre de marca
select c.idvehiculo,c.nombre,c.precio, deref(c.marca).idMarca as idMarca, deref(c.marca).nombre as NombreMarca from Coche c order by NombreMarca;

--ver colores de cada coche
select c.idvehiculo,c.nombre,c.precio,col.COLUMN_VALUE AS COLOR from Coche c, TABLE (c.colores) col;

--ver reparaciones de un coche
select c.idvehiculo,c.nombre,c.precio,rep.COLUMN_VALUE AS REPARACION from Coche c, TABLE (c.reparaciones) rep;

--numero de coches por marca
select deref(c.marca).nombre as NombreMarca, count(*) as NumCoches from Coche c group by deref(c.marca).nombre;

--diferentes marcas que existen
select distinct(deref(c.marca).nombre) from Coche c;

--coches de una marca concreta(por nombre)
select c.idvehiculo,
       c.nombre,
       c.precio
from Coche c
where deref(c.marca).nombre ='Jaime_Tesla';

--Reparaciones del coche 1
select rep.COLUMN_VALUE as Reparacion
from Coche c,
     TABLE(c.reparaciones) rep
where c.idvehiculo = 1;


--Colores del coche 1
select col.COLUMN_VALUE as Color
from Coche c,
     TABLE(c.colores) col
where c.idvehiculo = 1;