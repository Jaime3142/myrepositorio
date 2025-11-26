<?php
// conexion.php

// Las credenciales provienen del docker-compose.yml
$hostname = "db"; 
$usuario = "root";
$password = "root";
$base_datos = "bd_android";

// El @ suprime warnings si la conexión falla, evitando la impresión de HTML.
$conexion = @mysqli_connect($hostname, $usuario, $password, $base_datos);

if (!$conexion) {
    // Si la conexión falla, devuelve 'false' para que el script llamador lo maneje.
    return false; 
}

// Usamos utf8mb4 para mejor compatibilidad con caracteres.
mysqli_set_charset($conexion, "utf8mb4");

// Si la conexión es exitosa, $conexion contendrá el objeto de conexión.
// NO hay etiqueta de cierre ?>
