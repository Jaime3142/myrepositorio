<?php
$conexion = mysqli_connect("db", "root", "root", "bd_android");

if (!$conexion) {
    die("Error en la conexión: " . mysqli_connect_error());
}

mysqli_set_charset($conexion, "utf8mb4");
?>
