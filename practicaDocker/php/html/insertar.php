<?php
require_once 'conexion.php';
header('Content-Type: application/json');

$nombre = $_POST['nombre'] ?? '';
$psp = intval($_POST['psp'] ?? 0);
$ad = intval($_POST['ad'] ?? 0);
$ciber = intval($_POST['ciber'] ?? 0);
$ingles = intval($_POST['ingles'] ?? 0);
$interfaces = intval($_POST['interfaces'] ?? 0);

$sql = "INSERT INTO alumnos(nombre, psp, ad, ciberseguridad, ingles, interfaces)
        VALUES ('$nombre', $psp, $ad, $ciber, $ingles, $interfaces)";

if (mysqli_query($conexion, $sql)) {
    echo json_encode(['resultado' => 'OK']);
} else {
    echo json_encode([
        'resultado' => 'ERROR',
        'detalle' => mysqli_error($conexion)
    ]);
}

mysqli_close($conexion);

