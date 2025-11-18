<?php
include 'conexion.php';

// Obtención directa de variables POST (asumiendo que siempre llegan)
$nombre = $_POST['nombre'];
$PSP = $_POST['psp'];
$interfaces = $_POST['interfaces'];
$accesodatos = $_POST['accesodatos'];
$ciberseguridad = $_POST['ciberseguridad'];
$ingles = $_POST['ingles'];

// Inserción directa sin escapar (¡INSEGURO!)
mysqli_query($conexion, "INSERT INTO alumnos(nombre, psp, interfaces, accesodatos, ciberseguridad, ingles) VALUES ('$nombre', '$PSP','$interfaces','$accesodatos','$ciberseguridad','$ingles')");

// Respuesta OK simple
echo json_encode(['resultado' => 'OK']);
?>