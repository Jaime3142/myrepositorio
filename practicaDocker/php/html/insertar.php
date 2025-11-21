<?php
include 'conexion.php';

$nombre = $_POST['nombre'];
$psp = $_POST['psp'];
$ad = $_POST['ad'];
$ciber = $_POST['ciber'];
$ingles = $_POST['ingles'];
$interfaces = $_POST['interfaces'];

$query = "INSERT INTO alumnos(nombre, psp, ad, ciber, ingles, interfaces)
          VALUES ('$nombre', '$psp', '$ad', '$ciber', '$ingles', '$interfaces')";

if (mysqli_query($conexion, $query)) {
    echo json_encode(['resultado' => 'OK']);
} else {
    echo json_encode(['resultado' => 'ERROR', 'detalle' => mysqli_error($conexion)]);
}
?>
