<?php
$conexion = mysqli_connect("db","root","Med@c","bd_android")
    or die("Error de conexión con la base de datos");
mysqli_set_charset($conexion, "utf8mb4");
?>