<?php

require_once 'conexion.php';

header('Content-Type: application/json');

// Recoger el nombre enviado por GET
if (!isset($_GET['nombre']) || empty(trim($_GET['nombre']))) {
    echo json_encode(['error' => 'No se ha enviado nombre']);
    exit();
}

$nombre = mysqli_real_escape_string($conexion, trim($_GET['nombre']));

// Consulta para buscar el alumno por nombre (exacto)
$sql = "SELECT nombre, psp, ad, ciberseguridad AS ciber, ingles, interfaces FROM alumnos WHERE nombre = '$nombre' LIMIT 1";
$result = mysqli_query($conexion, $sql);

if (!$result) {
    http_response_code(500);
    echo json_encode(['error' => 'Error en la consulta', 'detalle' => mysqli_error($conexion)]);
    exit();
}

if (mysqli_num_rows($result) === 0) {
   
    echo json_encode(['error' => 'Alumno no encontrado']);
    exit();
}

// Obtener datos del alumno
$alumno = mysqli_fetch_assoc($result);


echo json_encode($alumno);

mysqli_close($conexion);
?>

