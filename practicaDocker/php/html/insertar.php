<?php
include 'conexion.php';

$nombre = $_POST['nombre'];
$psp = (int) $_POST['psp'];
$ad = (int) $_POST['ad'];
$ciber = (int) $_POST['ciber'];
$ingles = (int) $_POST['ingles'];
$interfaces = (int) $_POST['interfaces'];

$query = "INSERT INTO alumnos(nombre, psp, ad, ciber, ingles, interfaces)
          VALUES ('$nombre', $psp, $ad, $ciber, $ingles, $interfaces)";

if (mysqli_query($conexion, $query)) {
    echo json_encode(['resultado' => 'OK']);
} else {
    echo json_encode(['resultado' => 'ERROR', 'detalle' => mysqli_error($conexion)]);
}
?>
