<?php
// Las credenciales provienen del docker-compose.yml
$hostname = "db"; 
$usuario = "root";
$password = "root";
$base_datos = "bd_android";

// El @ suprime warnings si la conexión falla, evitando la impresión de HTML.
$conexion = @mysqli_connect($hostname, $usuario, $password, $base_datos);

if (!$conexion) { 
    return false; 
}

mysqli_set_charset($conexion, "utf8mb4");

 ?>
