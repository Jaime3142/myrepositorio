<?php
include 'conexion.php';
// Consulta directa y obtención de resultados en un bucle reducido
$res = mysqli_query($conexion, "SELECT * FROM alumnos");
$datos = [];
while ($f = mysqli_fetch_assoc($res)) $datos[] = $f;

// Salida JSON directa
echo json_encode($datos);
?>