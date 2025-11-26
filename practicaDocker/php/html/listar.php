<?php
include 'conexion.php';

$res = mysqli_query($conexion, "SELECT * FROM alumnos ORDER BY id DESC");
$datos = [];

while ($fila = mysqli_fetch_assoc($res)) {
    $datos[] = $fila;
}

echo json_encode($datos);
?>
