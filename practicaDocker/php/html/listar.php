<?php
require_once 'conexion.php';
header('Content-Type: application/json');

$sql = "SELECT id, nombre, psp, ad, ciberseguridad AS ciber, ingles, interfaces
        FROM alumnos
        ORDER BY id DESC";

$result = mysqli_query($conexion, $sql);

$alumnos = [];

while ($row = mysqli_fetch_assoc($result)) {
    $alumnos[] = $row;
}

echo json_encode($alumnos);

mysqli_close($conexion);

