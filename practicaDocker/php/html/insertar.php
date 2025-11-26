<?php
require_once 'conexion.php';
header('Content-Type: application/json');

$nombre = $_POST['nombre'] ?? '';
$psp = (int) ($_POST['psp'] ?? 0);
$ad = (int) ($_POST['ad'] ?? 0);
$ciber = (int) ($_POST['ciber'] ?? 0);
$ingles = (int) ($_POST['ingles'] ?? 0);
$interfaces = (int) ($_POST['interfaces'] ?? 0);

if ($nombre === "") {
    echo json_encode(['resultado' => 'ERROR', 'detalle' => 'Falta el nombre']);
    exit;
}

$query = "INSERT INTO alumnos(nombre, psp, ad, ciber, ingles, interfaces)
          VALUES ('$nombre', $psp, $ad, $ciber, $ingles, $interfaces)";

if (mysqli_query($conexion, $query)) {
    echo json_encode(['resultado' => 'OK']);
} else {
    echo json_encode(['resultado' => 'ERROR', 'detalle' => mysqli_error($conexion)]);
}

mysqli_close($conexion);

?>

